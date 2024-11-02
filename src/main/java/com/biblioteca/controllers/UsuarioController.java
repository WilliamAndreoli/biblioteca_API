package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.biblioteca.dto.UsuarioNoPassDTO;
import com.biblioteca.entities.Emprestimo;
import com.biblioteca.entities.Status;
import com.biblioteca.exceptions.UsuarioErrorException;
import com.biblioteca.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

	@Autowired
	private UsuarioDTOConverter usuarioDTOConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public List<UsuarioNoPassDTO> getAllUsuarios() {
		List<UsuarioNoPassDTO> usuariosDto = usuarioService.findAll();
		return usuariosDto;
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<UsuarioNoPassDTO> findById(@PathVariable Integer id) {
		UsuarioNoPassDTO usuarioDto = usuarioService.findById(id);
		if (usuarioDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarioDto);
	}

	@GetMapping("/emprestimos/{id}")
	public List<Emprestimo> getEmprestimosPorUsuario(@PathVariable Integer id) {
		return usuarioService.getEmprestimosDoUsuario(id);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioNoPassDTO> findByEmail(@PathVariable String email) {
		UsuarioNoPassDTO usuarioDto = usuarioService.findByEmailNoPass(email);
		if (usuarioDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarioDto);
	}

	@PostMapping
	public ResponseEntity<UsuarioNoPassDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		if (usuarioDTO.getStatus() == null) {
			usuarioDTO.setStatus(Status.ATIVO);
		}

		UsuarioNoPassDTO existingUsuario = usuarioService.findByEmailNoPass(usuarioDTO.getEmail());

		if (existingUsuario != null) {
			throw new UsuarioErrorException("Já existe um usuário com esse e-mail");
		}

		UsuarioNoPassDTO savedUsuario = usuarioService.save(usuarioDTO);
		return ResponseEntity.ok(savedUsuario);
	}

	@PutMapping("email/{email}")
    public ResponseEntity<UsuarioNoPassDTO> updateUsuario(@PathVariable String email, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setNome(usuarioDTO.getNome());
        System.out.println(email);
        System.out.println(usuarioDTO.getEmail());
        if (usuarioDTO.getEmail() == null) {
        	System.out.println("Entrando no if");
        	usuario.setEmail(email);
        } else {
        	usuario.setEmail(usuarioDTO.getEmail());
        }
        
    
        if (usuarioDTO.getSenha() == null) {
            usuario.setSenha(usuario.getSenha());
        } else {
        	usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));	
        }
     
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        UsuarioNoPassDTO updatedUsuario = usuarioService.update(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

	@PutMapping("status/{email}")
	public ResponseEntity<UsuarioNoPassDTO> alteraStatus(@PathVariable String email,
			@RequestBody UsuarioDTO usuarioDTO) {
		UsuarioDTO usuario = usuarioService.findByEmail(email);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		usuario.setStatus(usuarioDTO.getStatus());

		UsuarioNoPassDTO updatedUsuario = usuarioService.alteraStatus(usuario.getStatus(), email);
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
