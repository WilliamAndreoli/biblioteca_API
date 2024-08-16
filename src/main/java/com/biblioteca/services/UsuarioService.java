package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Usuario;
import com.biblioteca.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	// Deleta por nome
	@Transactional
	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
	}

	// Buscar por nome
	public List<Usuario> findByNome(String nome) {
		return usuarioRepository.findByNome(nome);
	}

	public Usuario findById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}

}
