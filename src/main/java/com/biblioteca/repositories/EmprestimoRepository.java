package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.dto.UsuarioNoPassDTO;
import com.biblioteca.entities.Emprestimo;
import com.biblioteca.entities.Usuario;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

	List<Emprestimo> findByUsuario(Usuario usuario);
	
}