package com.github.marcelofcamillo.libraryapi.repository;

import com.github.marcelofcamillo.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
