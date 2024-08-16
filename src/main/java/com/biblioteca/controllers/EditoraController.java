package com.biblioteca.controllers;

import java.util.List;

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

import com.biblioteca.entities.Editora;
import com.biblioteca.services.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

	@Autowired
	private EditoraService editoraService;

	@GetMapping
	public List<Editora> getAllEditoras() {
		return editoraService.findAll();
	}
	
	// Buscar por nome
    @GetMapping("/nome/{nome}")
    public Editora getEditoraByNome(@PathVariable String nome) {
        return editoraService.findByNome(nome);
    }
	
	
	@PostMapping
    public Editora createEditora(@RequestBody Editora editora) {
        return editoraService.save(editora);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Editora> updateEditora(@PathVariable String nome, @RequestBody Editora editoraDetails) {
        Editora editora = editoraService.findByNome(nome);
        if (editora == null) {
            return ResponseEntity.notFound().build();
        }
        editora.setNome(editoraDetails.getNome());
        editora.setEndereco(editoraDetails.getEndereco());
        return ResponseEntity.ok(editoraService.save(editora));
    }
	
	@DeleteMapping("/nome/{nome}")
    public ResponseEntity<Void> deleteEditoraByNome(@PathVariable String nome) {
        Editora editoras = editoraService.findByNome(nome);
        if (editoras == null) {
            return ResponseEntity.notFound().build();
        }
        editoraService.deleteByNome(nome);
        return ResponseEntity.noContent().build();
    }
	
}
