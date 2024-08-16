package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Editora;
import com.biblioteca.repositories.EditoraRepository;

@Service
public class EditoraService {

	@Autowired
	private EditoraRepository editoraRepository;
	
	public List<Editora> findAll() {
		return editoraRepository.findAll();
	}

	
}
