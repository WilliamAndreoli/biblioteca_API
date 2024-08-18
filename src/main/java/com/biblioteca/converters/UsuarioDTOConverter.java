package com.biblioteca.converters;

import org.springframework.stereotype.Component;

import com.biblioteca.dto.UsuarioDTO;
import com.biblioteca.entities.Usuario;
import com.biblioteca.services.Tipo_UsuarioService;

@Component
public class UsuarioDTOConverter {

    private final Tipo_UsuarioService tipoUsuarioService;

    public UsuarioDTOConverter(Tipo_UsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    public UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTipoUsuario(usuario.getTipo_usuario());
        return usuarioDTO;
    }

    public Usuario convertToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setTipo_usuario(tipoUsuarioService.findByDescricao(usuarioDTO.getTipoUsuario().getDescricao()));
        return usuario;
    }
}
