package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	List<Usuario> findByNome(String nome);
	
}
