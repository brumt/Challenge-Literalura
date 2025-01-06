package com.alura.literalura.service;

import com.alura.literalura.model.DataBook;
import java.util.List;

public class RespostaAPI {
    private int count; // Número total de resultados
    private String next; // Próxima página de resultados
    private String previous; // Página anterior de resultados
    private List<DataBook> results; // Lista de livros retornados pela API

    // Getters e Setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<DataBook> getResults() {
        return results;
    }

    public void setResults(List<DataBook> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "RespostaAPI{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}
