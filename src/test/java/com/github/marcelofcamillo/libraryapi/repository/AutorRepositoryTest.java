package com.github.marcelofcamillo.libraryapi.repository;

import com.github.marcelofcamillo.libraryapi.model.Autor;
import com.github.marcelofcamillo.libraryapi.model.GeneroLivro;
import com.github.marcelofcamillo.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Jose");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("2210bfb9-3570-41cf-b21a-5f982a03f6c0");
        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();

            System.out.println("Dados do Autor:");
            System.out.println(possivelAutor.get());

            autorEncontrado.setNome("João Silva");
            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));
            autorEncontrado.setNacionalidade("Espanhol");

            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void CountTest() {
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("bab93d91-9b7b-4ce0-a5fd-5f81ea8a11af");
        autorRepository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("9504bba3-d72d-4c37-ba31-290d6e1c980d");
        var jose = autorRepository.findById(id).get();
        autorRepository.delete(jose);
    }

    @Test
    public void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1970, 8, 5));

        Livro livro1 = new Livro();
        livro1.setIsbn("20847-84874");
        livro1.setPreco(BigDecimal.valueOf(204));
        livro1.setGenero(GeneroLivro.MISTERIO);
        livro1.setTitulo("O roubo da casa assombrada.");
        livro1.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro1.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("99599-56288");
        livro2.setPreco(BigDecimal.valueOf(143));
        livro2.setGenero(GeneroLivro.BIOGRAFIA);
        livro2.setTitulo("O Diário de Ana.");
        livro2.setDataPublicacao(LocalDate.of(2005, 9, 7));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro1);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);
        //livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor() {
        var id = UUID.fromString("38602241-d428-4b1e-abe1-95d1df7fe24d");
        var autor = autorRepository.findById(id).get();

        // busca os livros do autor
        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}
