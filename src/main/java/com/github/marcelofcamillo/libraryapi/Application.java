package com.github.marcelofcamillo.libraryapi;

import com.github.marcelofcamillo.libraryapi.model.Autor;
import com.github.marcelofcamillo.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
