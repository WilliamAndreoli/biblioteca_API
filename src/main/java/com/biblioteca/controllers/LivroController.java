package com.biblioteca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entities.Livro;
import com.biblioteca.entities.Livro;
import com.biblioteca.services.LivroService;

@RestController
@RequestMapping("/livros")
@CrossOrigin
public class LivroController {

	@Autowired
	private LivroService livroService;
	
	@GetMapping
	public List<Livro> getAllLivros() {
		return livroService.findAll();
	}

	@GetMapping("/titulo/{titulo}")
	public Optional<Livro> getLivroById(@PathVariable String titulo) {
		return livroService.findByTitulo(titulo);
	}

	@GetMapping("/autor/{nomeAutor}")
    public ResponseEntity<List<Livro>> getLivrosByAutor(@PathVariable String nomeAutor) {
        List<Livro> livros = livroService.findLivrosByAutor(nomeAutor);
        return ResponseEntity.ok(livros);
    }
	
	@GetMapping("/area/{descricaoArea}")
    public ResponseEntity<List<Livro>> getLivrosByArea(@PathVariable String descricaoArea) {
        List<Livro> livros = livroService.findLivrosByArea(descricaoArea);
        return ResponseEntity.ok(livros);
    }
	
	@GetMapping("/editora/{editoraNome}")
    public ResponseEntity<List<Livro>> getLivrosByEditora(@PathVariable String editoraNome) {
        List<Livro> livros = livroService.findLivrosByEditora(editoraNome);
        return ResponseEntity.ok(livros);
    }
	
	@PostMapping
	public Livro createLivro(@RequestBody Livro livro) {
		return livroService.save(livro);
	}

	@PutMapping("/titulo/{titulo}")
	public ResponseEntity<Livro> updateLivro(@PathVariable String titulo,
			@RequestBody Livro livroDetails) {
		Optional<Livro> optionalLivro = livroService.findByTitulo(titulo);

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
		livro.setImg(livroDetails.getImg());

		return ResponseEntity.ok(livroService.update(livro));
	}
	
	@PutMapping("status/{titulo}")
	public ResponseEntity<Livro> alteraStatus(@PathVariable String titulo, @RequestBody Livro livroDetails) throws Exception {
		Optional<Livro> optionalLivro = livroService.findByTitulo(titulo);

		if (!optionalLivro.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Livro livro = optionalLivro.get();
		
		livro.setStatus(livroDetails.getStatus());
		
		Livro updatedLivro = livroService.alteraStatus(livro.getStatus(), titulo);
		return ResponseEntity.ok(livroService.save(livro));
	}

	@DeleteMapping("/titulo/{titulo}")
	public ResponseEntity<Void> deleteLivroById(@PathVariable String titulo) {
		Optional<Livro> optionalLivro = livroService.findByTitulo(titulo);

		if (!optionalLivro.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		livroService.deleteByTitulo(titulo);
		return ResponseEntity.noContent().build();
	}
	
}
