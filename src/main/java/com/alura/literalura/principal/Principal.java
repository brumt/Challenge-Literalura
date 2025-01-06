package com.alura.literalura.principal;

import com.alura.literalura.model.Author;
import com.alura.literalura.model.DataBook;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AuthorRepository;
import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.service.Api;
import com.alura.literalura.service.Convert;
import com.alura.literalura.service.RespostaAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private Api api = new Api();
    private Convert conversor = new Convert();
    private Scanner leitura = new Scanner(System.in);
    private List<DataBook> dataBooks = new ArrayList<>();

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public void menu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1  - Buscar Livro pelo título
                    2  - Listar livros registrados
                    3  - Listar autores registrados
                    4  - Listar autores vivos em um determinado ano
                    5  - Listar livros em um determinado idíoma
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    searchBook();
                    break;
                case 2:
                    ListRegistredBooks();
                    break;
                case 3:
                    listRegistredAuthors();
                    break;
                case 4:
                    listAuthorsByYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }


    private void searchBook() {
        System.out.println("Digite o nome do livro para busca:");
        String booksName = leitura.nextLine();


        var existentBook = bookRepository.findByTitleContainingIgnoreCase(booksName);
        if (existentBook.isPresent()) {
            System.out.println("Livro encontrado no banco de dados:");
            System.out.println(existentBook.get());
            return;
        }


        DataBook data = getDataBook(booksName);
        if (data == null) {
            System.out.println("Livro não encontrado!!!");
            return;
        }

        // Salva o livro no banco de dados
        saveBook(data);
        System.out.println("Livro salvo no banco de dados.");
        System.out.println(data);
    }

    private DataBook getDataBook(String booksName) {
        String enderecoBusca = ENDERECO + booksName.replace(" ", "+");
        System.out.println(enderecoBusca);
        // Chama o consumo da API e obtém o JSON
        var json = api.getData(enderecoBusca);


        if (json.contains("{\"count\":0,\"next\":null,\"previous\":null,\"results\":[]}")) {
            return null; // Retorna null se não encontrar o livro
        } else {
            // Deserializa o JSON para a classe RespostaAPI
            RespostaAPI resposta = conversor.getData(json, RespostaAPI.class);

            // Pega a lista de livros do campo 'results'
            List<DataBook> books = resposta.getResults();

            // Se encontrar livros, retorna o primeiro (ou qualquer outro tratamento desejado)
            if (!books.isEmpty()) {
                DataBook data = books.get(0); // Aqui você pode ajustar caso queira retornar outro livro ou lista
                return data;
            } else {
                return null; // Caso não tenha livros na lista
            }
        }
    }

    private void saveBook(DataBook dataBook) {
        Livro book = new Livro();
        book.setTitle(dataBook.title());
        book.setDownloads(dataBook.downloads());
        book.setLanguage(dataBook.languages().get(0)); // Define o primeiro idioma para simplificar

        System.out.println(dataBook.authors().get(0).name());
        Author author ;
        var existentAuthor = authorRepository.findByName(dataBook.authors().get(0).name());

        if (existentAuthor.isPresent()) {

            author = existentAuthor.get();

        } else {
            author = new Author();
            author.setName(dataBook.authors().get(0).name());  // Assume que o livro tem pelo menos um author
            author.setBirthYear(dataBook.authors().get(0).birthYear());
            author.setDeathYear(dataBook.authors().get(0).deathYear());
            authorRepository.save(author);

        }
        book.setAuthor(author);
        bookRepository.save(book);
    }

    private void ListRegistredBooks () {
        var books = bookRepository.findAll();
        books.forEach(System.out::println);
    }

    private void listRegistredAuthors () {
        var authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }
    private void listAuthorsByYear() {
        System.out.println("Informe o ano que  deseja saber se o autor esta vivo :");
        var year = leitura.nextInt();
        List<Author> aliveAuthor =  authorRepository.findByDeathYearGreaterThan(year);
        aliveAuthor.forEach(System.out::println);
    }

    private void listBooksByLanguage() {
        System.out.println("Informe o idioma que deseja listar os livros  Português, Inglês ou Espanhol :");
        var language = leitura.nextLine();
        if (language.equals("Português")){
            language = "pt";
        } else if(language.equals("Inglês")) {
            language = "en";
        } else if(language.equals("Espanhol")){
            language = "es";
        } else {
            System.out.println("Lingua não definida!!!");
            return;
        }
        List<Livro> bookByLanguage = bookRepository.booksByLanguage(language);
        bookByLanguage.forEach(System.out::println);

    }

}