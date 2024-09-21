package com.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Usuario;
import com.biblioteca.exceptions.LoginErrorException;
import com.biblioteca.repositories.UsuarioRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if (usuario != null) {
			return UserDetailsImpl.build(usuario);
		} else {
			throw new LoginErrorException("Credências Inválidas, tente novamente");
		}
		
		
	}

}
