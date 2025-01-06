package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String title;

    private int downloads;
    private String language;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    public Livro() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "--------------------- LIVRO ---------------------\n" +
                // "id=" + id +
                "Título              : " + title +  "\n" +
                "Autor               : " + author +  "\n"  +
                "idioma              : " + language +  "\n" +
                "Número de downloads : " + downloads +"\n"+
                "--------------------------------------------------\n" ;
    }
}