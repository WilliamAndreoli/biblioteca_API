package com.biblioteca.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biblioteca.dto.UsuarioDTO;
import com.biblioteca.entities.Tipo_Usuario;
import com.biblioteca.entities.Usuario;
import com.biblioteca.repositories.Tipo_UsuarioRepository;
import com.biblioteca.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private Tipo_UsuarioRepository tipoUsuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public UsuarioDTO save(UsuarioDTO usuarioDto) {
        Usuario usuario = convertToEntity(usuarioDto);
        
        // Verificando se o Tipo_Usuario já existe
        Tipo_Usuario tipoUsuario = usuario.getTipo_usuario();
        if (tipoUsuario != null) { 
        	if (tipoUsuario.getDescricao() != null) {
        		// Buscar o Tipo_usuario existente
        		Tipo_Usuario existingTipoUsuario = tipoUsuarioRepository.findByDescricao(tipoUsuario.getDescricao());
        		if (existingTipoUsuario != null) {
                    // Se o Tipo_Usuario existe, associe-o ao Usuario
                    usuario.setTipo_usuario(existingTipoUsuario);
                } else {
                    // Se o Tipo_Usuario não existe, salve o novo Tipo_Usuario
                    tipoUsuario = tipoUsuarioRepository.save(tipoUsuario);
                    usuario.setTipo_usuario(tipoUsuario);
                }
        	} else {
                throw new RuntimeException("Tipo Usuário nulo ou inexistente: " + tipoUsuario.getDescricao());
            }
        }
        
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDto(savedUsuario);
    }

    @Transactional
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public List<UsuarioDTO> findByNome(String nome) {
        return usuarioRepository.findByNome(nome).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
    
    public UsuarioDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return convertToDto(usuario);
        }
        // Lançar uma exceção ou retornar null se o usuário não for encontrado
        return null;
    }

    public UsuarioDTO findById(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario != null ? convertToDto(usuario) : null;
    }

    // Métodos auxiliares para conversão
    private UsuarioDTO convertToDto(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.getTipo_usuario()
        );
    }

    private Usuario convertToEntity(UsuarioDTO usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setTipo_usuario(tipoUsuarioRepository.findByDescricao(usuarioDto.getTipoUsuario().getDescricao()));
        /*
        if (usuarioDto.getTipoUsuario() != null) {
        	Tipo_Usuario tipoUsuario = new Tipo_Usuario();
        	tipoUsuario.setId(usuarioDto.getTipoUsuario().getId());
            tipoUsuario.setDescricao(usuarioDto.getTipoUsuario().getDescricao());
            tipoUsuario.setDias_emprestimo(usuarioDto.getTipoUsuario().getDias_emprestimo());
            tipoUsuario.setMulta_diaria(usuarioDto.getTipoUsuario().getMulta_diaria());
            usuario.setTipo_usuario(tipoUsuario);
        } else {
        	throw new IllegalArgumentException("Tipo de usuário não pode ser nulo");
        }*/

        return usuario;
    }
}