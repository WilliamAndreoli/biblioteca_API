package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@DeleteMapping("/nome/{nome}")
    public ResponseEntity<Void> deleteEditoraByNome(@PathVariable String nome) {
        List<Editora> editoras = editoraService.findByNome(nome);
        if (editoras.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        editoraService.deleteByNome(nome);
        return ResponseEntity.noContent().build();
    }
	
	// Buscar por nome
    @GetMapping("/nome/{nome}")
    public List<Editora> getEditoraByNome(@PathVariable String nome) {
        return editoraService.findByNome(nome);
    }
	
}
