package com.biblioteca.services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.biblioteca.entities.Usuario;

public class UserDetailsImpl implements UserDetails{

	private Integer id;
	
	private String nome;
	
	private String userName;
	
	private String email;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public static UserDetailsImpl build(Usuario usuario) {
	    return new UserDetailsImpl(
	            usuario.getId(),
	            usuario.getNome(),
	            usuario.getEmail(),
	            usuario.getEmail(),
	            usuario.getSenha(),
	            usuario.getAuthorities() // Usando o tipoUsuario para definir a autoridade
	    );
	}
	
	public UserDetailsImpl(Integer id, String nome, String userName, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.nome = nome;
		this.userName = email;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

}
