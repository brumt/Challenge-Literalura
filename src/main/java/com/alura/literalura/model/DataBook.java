package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<DataAuthor> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") int downloads
) {
    @Override
    public String toString() {
        String authorsNames = authors.stream()
                .map(DataAuthor::name)
                .collect(Collectors.joining(", "));

        return "--------------------- LIVRO ---------------------\n" +
                "Título              : " + title + "\n" +
                "Autor(es)           : " + authorsNames + "\n" +
                "Idioma(s)           : " + String.join(", ", languages) + "\n" +
                "Número de downloads : " + downloads + "\n" +
                "--------------------------------------------------";
    }


    public List<String> getLanguagesWithNames() {
        Map<String, String> languageMap = Map.of(
                "pt", "Portuguese",
                "en", "English",
                "es", "Spanish"

        );

        return languages.stream()
                .map(languageMap::get)
                .toList();
    }
}
