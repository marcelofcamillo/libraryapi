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
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {
    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Ciências");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("0682af1a-532d-4917-a1c0-638ae77d99d2"))
                .orElse(null);

        //livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Ciências");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Jose");
        autor.setNacionalidade("Francês");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        //livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro Livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Ricardo");
        autor.setNacionalidade("Alemão");
        autor.setDataNascimento(LocalDate.of(1985, 3, 22));

        autorRepository.save(autor);
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        UUID idLivro = UUID.fromString("5a72dcdc-4a5f-4ccc-9e56-c5741de6a029");
        var livroParaAtualizar = livroRepository.findById(idLivro).orElse(null);

        UUID idAutor = UUID.fromString("2b5937d3-ee46-4471-b882-342dff139a00");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);
        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deletarTest() {
        UUID id = UUID.fromString("9fc05663-bad2-46f5-9916-259489150cb9");
        livroRepository.deleteById(id);
    }

    @Test
    void deletarCascadeTest() {
        UUID id = UUID.fromString("6089e630-01c1-47f3-9d1b-33621c4053b6");
        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("e942faef-2fca-41ec-9c23-219cbb9681a1");
        Livro livro = livroRepository.findById(id).orElse(null);

        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest() {
        List<Livro> lista = livroRepository.findByTitulo("O Diário de Ana.");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbn() {
        List<Livro> lista = livroRepository.findByIsbn("20847-84874");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloEPreco() {
        var preco = BigDecimal.valueOf(204);
        var titulo = "O roubo da casa assombrada.";
        List<Livro> lista = livroRepository.findByTituloAndPreco(titulo, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloOuIsbn() {
        var isbn = "20847-84874";
        var titulo = "O Diário de Ana.";
        List<Livro> lista = livroRepository.findByTituloOrIsbn(titulo, isbn);
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorDataPublicacao() {
        var inicio = LocalDate.of(1980, 1, 2);
        var fim = LocalDate.of(2005, 9, 7);
        List<Livro> lista = livroRepository.findByDataPublicacaoBetween(inicio, fim);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL() {
        var resultado = livroRepository.listarTodosOrdenadoPorTituloEPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros() {
        var resultado = livroRepository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepetidosDosLivros() {
        var resultado = livroRepository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresBrasileiros() {
        var resultado = livroRepository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParam() {
        var resultado = livroRepository.findByGenero(GeneroLivro.FICCAO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    // positional parameters
    @Test
    void listarPorGeneroPositionalParameters() {
        var resultado = livroRepository.findByGeneroPositionalParameters(GeneroLivro.FICCAO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest() {
        livroRepository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    void updateDataPublicacaoTest() {
        livroRepository.updateDataPublicacao(LocalDate.of(2000,1,1));
    }
}