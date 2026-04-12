package com.github.marcelofcamillo.libraryapi.repository;

import com.github.marcelofcamillo.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Jose");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("2210bfb9-3570-41cf-b21a-5f982a03f6c0");
        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();

            System.out.println("Dados do Autor:");
            System.out.println(possivelAutor.get());

            autorEncontrado.setNome("João Silva");
            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));
            autorEncontrado.setNacionalidade("Espanhol");

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void CountTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("bab93d91-9b7b-4ce0-a5fd-5f81ea8a11af");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("9504bba3-d72d-4c37-ba31-290d6e1c980d");
        var jose = repository.findById(id).get();
        repository.delete(jose);
    }
}
