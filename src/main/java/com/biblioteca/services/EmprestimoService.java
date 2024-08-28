package com.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.biblioteca.entities.Emprestimo;
import com.biblioteca.entities.Livro;
import com.biblioteca.entities.Usuario;
import com.biblioteca.repositories.EmprestimoRepository;
import com.biblioteca.repositories.LivroRepository;
import com.biblioteca.repositories.Tipo_UsuarioRepository;
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
	
	@Autowired
	private Tipo_UsuarioRepository tipo_UsuarioRepository;

	public List<Emprestimo> findAll() {
		List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        emprestimos.forEach(Emprestimo::calcularMulta);
        return emprestimos;
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
				throw new RuntimeException("Usuario inexistente");
			}
		}

		if (livro != null) {

			if (livro.getTitulo() != null) {
				Livro existingLivro = livroRepository.findByTitulo(livro.getTitulo());
				if (existingLivro != null) {
					
					if (existingLivro.getQuantidadeDisponivel() > 0) {
						existingLivro.setQuantidadeDisponivel(existingLivro.getQuantidadeDisponivel() - 1);
						livroRepository.save(existingLivro);
						emprestimo.setLivro(existingLivro);
					}
				}

			} else {
				throw new RuntimeException("Livro indisponível para empréstimo.");
			}

		}
		emprestimo.calcularMulta();
		return emprestimoRepository.save(emprestimo);
	}
	
	public Emprestimo devolucaoLivro(Emprestimo emprestimo) {
		
		Livro livro = emprestimo.getLivro();
		
		if (livro.getQuantidadeDisponivel() >= 0 && livro.getQuantidadeDisponivel() < livro.getQuantidade()) {
			livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
			livroRepository.save(livro);
		}
		
		emprestimo.calcularMulta();
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