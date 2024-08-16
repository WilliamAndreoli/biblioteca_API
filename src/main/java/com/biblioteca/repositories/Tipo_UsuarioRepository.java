package com.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.Tipo_Usuario;

public interface Tipo_UsuarioRepository extends JpaRepository<Tipo_Usuario, Integer> {

	Tipo_Usuario findByDescricao(String descricao);
	
	void deleteByDescricao(String descricao);
	
}
