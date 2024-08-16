package com.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.Autor;
import com.biblioteca.entities.Editora;

public interface AutorRepository extends JpaRepository<Autor, Integer>{
	
	Autor findByNome(String nome);
	
	void deleteByNome(String nome);
	
}
