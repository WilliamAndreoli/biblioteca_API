package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.Editora;

public interface EditoraRepository extends JpaRepository<Editora, Integer>{

	Editora findByNome(String nome);
	
	void deleteByNome(String nome);
	
}
