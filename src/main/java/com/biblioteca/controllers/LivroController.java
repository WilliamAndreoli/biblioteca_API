package com.biblioteca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entities.Livro;
import com.biblioteca.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;
	
	@GetMapping
	public List<Livro> getAllLivros() {
		return livroService.findAll();
	}

	@GetMapping("/id/{id}")
	public Optional<Livro> getLivroById(@PathVariable Integer id) {
		return livroService.findById(id);
	}

	
	@PostMapping
	public Livro createLivro(@RequestBody Livro livro) {
		return livroService.save(livro);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Livro> updateLivro(@PathVariable Integer id,
			@RequestBody Livro livroDetails) {
		Optional<Livro> optionalLivro = livroService.findById(id);

		if (!optionalLivro.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Livro livro = optionalLivro.get();
		livro.setTitulo(livroDetails.getTitulo());
		livro.setResumo(livroDetails.getResumo());
		livro.setQuantidade(livroDetails.getQuantidade());
		livro.setEditora(livroDetails.getEditora());
		livro.setEdicao(livroDetails.getEdicao());
		livro.setCodigo(livroDetails.getCodigo());
		livro.setArea(livroDetails.getArea());
		livro.setAno_publicacao(livroDetails.getAno_publicacao());

		return ResponseEntity.ok(livroService.save(livro));
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<Void> deleteLivroById(@PathVariable Integer id) {
		Optional<Livro> optionalLivro = livroService.findById(id);

		if (!optionalLivro.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		livroService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
