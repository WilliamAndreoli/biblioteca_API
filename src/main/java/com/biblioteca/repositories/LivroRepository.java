package com.biblioteca.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblioteca.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

	Optional<Livro> findByTitulo(String titulo);

	void deleteByTitulo(String titulo);
	
	@Query("SELECT l FROM Livro l JOIN l.autores a WHERE a.nome = :nomeAutor")
    List<Livro> findByAutorNome(@Param("nomeAutor") String nomeAutor);
	
	List<Livro> findByAreaDescricao(String descricaoArea);
	
	List<Livro> findByEditoraNome(String nomeEditora);
}
