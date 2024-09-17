package com.biblioteca.services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.biblioteca.entities.Usuario;

public class UserDetailsImpl implements UserDetails{

	private Integer id;
	
	private String name;
	
	private String userName;
	
	private String email;
	
	private String password;
	
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
	
	public UserDetailsImpl(Integer id, String name, String userName, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.userName = email;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}



	private Collection<? extends GrantedAuthority> authorities;

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
