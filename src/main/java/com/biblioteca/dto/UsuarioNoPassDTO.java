package com.biblioteca.dto;

import com.biblioteca.entities.Status;
import com.biblioteca.entities.Tipo_Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioNoPassDTO {

	private Integer id;
    private String nome;
    private String email;
    @JsonFormat
    private Status status = Status.ATIVO;
    private Tipo_Usuario tipoUsuario;

    // Construtores
    public UsuarioNoPassDTO() {}

    public UsuarioNoPassDTO(Integer id, String nome, String email, Status status, Tipo_Usuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.status = status;
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

    public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Tipo_Usuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Tipo_Usuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
	
}
