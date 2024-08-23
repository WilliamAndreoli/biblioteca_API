package com.biblioteca.dto;

import com.biblioteca.entities.Tipo_Usuario;

public class UsuarioDTO {

	private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Tipo_Usuario tipoUsuario;

    // Construtores
    public UsuarioDTO() {}

    public UsuarioDTO(Integer id, String nome, String email, String senha, Tipo_Usuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Tipo_Usuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Tipo_Usuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    
    
	
}
