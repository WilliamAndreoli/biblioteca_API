package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.converters.UsuarioDTOConverter;
import com.biblioteca.dto.UsuarioDTO;
import com.biblioteca.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDTOConverter usuarioDTOConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        List<UsuarioDTO> usuariosDto = usuarioService.findAll();
        return usuariosDto;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
        UsuarioDTO usuarioDto = usuarioService.findById(id);
        if (usuarioDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioDto);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email) {
        UsuarioDTO usuarioDto = usuarioService.findByEmail(email);
        if (usuarioDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioDto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO savedUsuario = usuarioService.save(usuarioDTO);
        return ResponseEntity.ok(savedUsuario);
    }

    @PutMapping("email/{email}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable String email, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        UsuarioDTO updatedUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable String email) {
        UsuarioDTO usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
