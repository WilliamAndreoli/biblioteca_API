package com.biblioteca.entities;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String email;
	
	private String senha;

	@Enumerated(EnumType.STRING) // Usando EnumType.STRING para armazenar o nome do enum
    private Status status;
	
	@ManyToOne
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
	private Tipo_Usuario tipo_Usuario;
	
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
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Tipo_Usuario getTipo_Usuario() {
		return tipo_Usuario;
	}
	
	public void setTipo_Usuario(Tipo_Usuario tipo_usuario) {
		this.tipo_Usuario = tipo_usuario;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + tipo_Usuario.getDescricao().toUpperCase()));
    }
	
	public void alteraStatus(Status status) {
		if(status != null) {
			
			if (status == status.ATIVO) {
				this.status = status.ATIVO;
			} if(status == status.INATIVO) {
				this.status = status.INATIVO;
			}
			
		} else {
			//Tratativa
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
