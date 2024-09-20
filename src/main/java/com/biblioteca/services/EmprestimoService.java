package com.biblioteca.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Emprestimo;
import com.biblioteca.entities.Livro;
import com.biblioteca.entities.Usuario;
import com.biblioteca.exceptions.UsuarioNotFoundException;
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
				throw new UsuarioNotFoundException("Usuario inexistente.");
			}
		}

		if (livro != null) {

			if (livro.getTitulo() != null) {
				Optional<Livro> optionalLivro = livroRepository.findByTitulo(livro.getTitulo());

				if (optionalLivro.get() != null) {

					Livro existingLivro = optionalLivro.get();

					if (existingLivro.getQuantidadeDisponivel() > 0) {
						existingLivro.setQuantidadeDisponivel(existingLivro.getQuantidadeDisponivel() - 1);
						livroRepository.save(existingLivro);
						emprestimo.setLivro(existingLivro);
					} else {
						throw new RuntimeException("Livro indisponível para empréstimo.");
					}
				}

			} else {
				throw new RuntimeException("Livro inexistente.");
			}

		}
		
		emprestimo.setData_emprestimo(new Date());
		
		// Calcula a data de previsão com base no tipo de usuário
        Calendar cal = Calendar.getInstance();
        cal.setTime(emprestimo.getData_emprestimo());
        cal.add(Calendar.DAY_OF_MONTH, emprestimo.getUsuario().getTipo_Usuario().getDias_emprestimo());
        emprestimo.setData_previsao(cal.getTime());
		
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