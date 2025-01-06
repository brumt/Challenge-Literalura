package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;

public record DataAuthor(@JsonAlias("name") String name,
                         @JsonAlias("birth_year") Integer birthYear,
                         @JsonAlias("death_year") Integer deathYear) {
}