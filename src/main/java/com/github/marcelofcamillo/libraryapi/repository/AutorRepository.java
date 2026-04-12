package com.github.marcelofcamillo.libraryapi.repository;

import com.github.marcelofcamillo.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
