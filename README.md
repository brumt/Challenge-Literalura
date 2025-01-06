# Challenge-Literalura

Challenge proporcionado por Alura + Oracle One ✨

# 📖 Literalura
É um projeto desenvolvido como parte do challenge LiterAlura, promovido pela Alura em parceria com o programa Oracle Next Education. A aplicação permite pesquisar e registrar livros utilizando a API Gutendex, armazenando os dados de forma persistente em um banco de dados PostgreSQL.

## 🛠️ Tecnologias Utilizadas
- Linguagem de Programação: Java
- Framework: Spring Boot
- Gerenciamento de Dependências: Maven
- Banco de Dados: PostgreSQL
- API Externa: Gutendex
- Persistência de Dados: Spring Data JPA
 
## 📦 Dependências
- Spring Boot Starter Web – Para a construção da API REST.
- Spring Data JPA – Para facilitar a comunicação com o banco de dados PostgreSQL.
- PostgreSQL Driver – Para a conexão da aplicação ao banco de dados.
 
## 🌍 Funcionalidades
A aplicação interage com a API Gutendex para realizar diversas operações, incluindo:

### 🔍 1. Busca de Livros
O usuário pode pesquisar livros pelo título, e a aplicação retorna informações como:
- Nome do livro
- Autor(es)
- Idioma
- Número de downloads
 
### 💾 2. Registro de Livros no Banco de Dados
Caso o livro pesquisado ainda não esteja cadastrado, ele será salvo no banco de dados com as seguintes informações:
- Título
- Autor(es)
- Idioma
- Número de downloads
 
### 📚 3. Consulta de Livros Registrados
Exibe uma lista de todos os livros já armazenados no banco de dados.

### 🖊️ 4. Listagem de Autores
Permite visualizar todos os autores presentes na base de dados.

### 📆 5. Pesquisa de Autores Vivos em um Ano Específico
O usuário pode inserir um ano e visualizar quais autores estavam vivos naquele período.

### 🌎 6. Filtragem de Livros por Idioma
Possibilita listar todos os livros disponíveis em um determinado idioma.

