package com.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Emprestimo;
import com.biblioteca.entities.Livro;
import com.biblioteca.entities.Usuario;
import com.biblioteca.repositories.EmprestimoRepository;
import com.biblioteca.repositories.LivroRepository;
import com.biblioteca.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private LivroRepository livroRepository;

	public List<Emprestimo> findAll() {
		return emprestimoRepository.findAll();
	}

	public Emprestimo save(Emprestimo emprestimo) throws Exception {
		Usuario usuario = emprestimo.getUsuario();

		Livro livro = emprestimo.getLivro();

		if (usuario != null) {
			if (usuario.getId() != null) {
				// Buscar o Usuario existente
				Usuario existingUsuario = usuarioRepository.findById(usuario.getId()).orElse(null);

				if (existingUsuario != null) {
					// Se o Usuario existe, associe-o ao Emprestimo
					emprestimo.setUsuario(existingUsuario);
				}
			} else {
				throw new Exception("Usuario inexistente");
			}
		}

		if (livro != null) {

			if (livro.getTitulo() != null) {
				Livro existingLivro = livroRepository.findByTitulo(livro.getTitulo());
				if (existingLivro != null) {
					emprestimo.setLivro(existingLivro);
				}

			} else {
				throw new Exception("Livro inexistente");
			}

		}

		return emprestimoRepository.save(emprestimo);
	}

	@Transactional
	public void deleteById(Integer id) {
		emprestimoRepository.deleteById(id);
	}

	public Optional<Emprestimo> findById(Integer id) {
		return emprestimoRepository.findById(id);
	}

}
