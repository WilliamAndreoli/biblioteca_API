package com.biblioteca.controllers;

import java.util.List;

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

import com.biblioteca.entities.Autor;
import com.biblioteca.services.AutorService;

@RestController
@RequestMapping("/autores")
@CrossOrigin
public class AutorController {

	@Autowired
	private AutorService autorService;

	@GetMapping
	public List<Autor> getAllAutores() {
		return autorService.findAll();
	}
	
	// Buscar por nome
    @GetMapping("/nome/{nome}")
    public Autor findByNome(@PathVariable String nome) {
    	return autorService.findByNome(nome);
    }
	
    @PostMapping
    public Autor createAutor(@RequestBody Autor autor) {
        return autorService.save(autor);
    }
	
	@PutMapping("nome/{nome}")
    public ResponseEntity<Autor> updateAutor(@PathVariable String nome, @RequestBody Autor autorDetails) {
        Autor autor = autorService.findByNome(nome);
        if (autor == null) {
            return ResponseEntity.notFound().build();
        }
        autor.setNome(autorDetails.getNome());
        autor.setEndereco(autorDetails.getEndereco());
        return ResponseEntity.ok(autorService.update(autor));
    }
	
	@PutMapping("status/{nome}")
	public ResponseEntity<Autor> alteraStatus(@PathVariable String nome, @RequestBody Autor autorDetails) {
		Autor autor = autorService.findByNome(nome);
		if (autor == null) {
            return ResponseEntity.notFound().build();
        }
		
		autor.setStatus(autorDetails.getStatus());
		
		Autor updatedAutor = autorService.alteraStatus(autor.getStatus(), nome);
		return ResponseEntity.ok(autorService.save(autor));
	}
	
	@DeleteMapping("/nome/{nome}")
    public ResponseEntity<Void> deleteAutorByNome(@PathVariable String nome) {
        Autor autor = autorService.findByNome(nome);
        if (autor == null) {
            return ResponseEntity.notFound().build();
        }
        autorService.deleteByNome(nome);
        return ResponseEntity.noContent().build();
    }
    
}
