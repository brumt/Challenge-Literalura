package com.alura.literalura.repository;

import com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Livro, Long> {

    // Buscar livros pelo título (case insensitive)

    Optional<Livro>findByTitleContainingIgnoreCase(String title);
    @Query("SELECT l FROM Livro l WHERE l.language = :language")
    List<Livro> booksByLanguage(@Param("language") String language);
}
