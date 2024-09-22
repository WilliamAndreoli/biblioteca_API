package com.biblioteca.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

	Optional<Livro> findByTitulo(String titulo);

	void deleteByTitulo(String titulo);
	
}
