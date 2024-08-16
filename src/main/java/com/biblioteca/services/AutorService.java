package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Autor;
import com.biblioteca.repositories.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	public List<Autor> findAll() {
		return autorRepository.findAll();
	}

	
}
