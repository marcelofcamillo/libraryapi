package com.github.marcelofcamillo.libraryapi.repository;

import com.github.marcelofcamillo.libraryapi.model.Autor;
import com.github.marcelofcamillo.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */
public interface LivroRepository extends JpaRepository<Livro, UUID> {
    // Query Method

    // SELECT * FROM livro WHERE id_autor = id
    List<Livro> findByAutor(Autor autor);

    // SELECT * FROM livro WHERE titulo = ?
    List<Livro> findByTitulo(String titulo);

    // SELECT * FROM livro WHERE isbn = ?
    List<Livro> findByIsbn(String isbn);

    // SELECT * FROM livro WHERE titulo = ? AND preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    // SELECT * FROM livro WHERE titulo = ? OR isbn = ?
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    // SELECT * FROM livro WHERE data_publicacao BETWEEN ? AND ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    // JPQL: referencia as entidades e as propriedades
    @Query("SELECT l FROM Livro AS l ORDER BY l.titulo, l.preco")
    List<Livro> listarTodosOrdenadoPorTituloEPreco();

    @Query("SELECT a FROM Livro l JOIN l.autor a")
    List<Autor> listarAutoresDosLivros();

    @Query("SELECT DISTINCT l.titulo FROM Livro l")
    List<String> listarNomesDiferentesLivros();

    @Query("""
             SELECT l.genero
             FROM Livro l
             JOIN l.autor a
             WHERE a.nacionalidade = 'Brasileira'
             ORDER BY l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();
}
