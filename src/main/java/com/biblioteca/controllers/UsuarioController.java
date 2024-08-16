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

import com.biblioteca.entities.Usuario;
import com.biblioteca.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> getAllUsuarios() {
		return usuarioService.findAll();
	}
	
    @GetMapping("/id/{id}")
    public Usuario findById(@PathVariable Integer id) {
    	return usuarioService.findById(id);
    }
	
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        Usuario usuario= usuarioService.findById(id);
        if (usuario== null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setNome(usuarioDetails.getNome());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setSenha(usuarioDetails.getSenha());
        usuario.setTipo_usuario(usuarioDetails.getTipo_usuario());
        return ResponseEntity.ok(usuarioService.save(usuario));
    }
	
	@DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Integer id) {
        Usuario usuario= usuarioService.findById(id);
        if (usuario== null) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
