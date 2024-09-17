package com.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

	Livro findByTitulo(String titulo);
	
}
