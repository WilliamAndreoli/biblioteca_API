package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Autor;
import com.biblioteca.repositories.AutorRepository;

import jakarta.transaction.Transactional;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	public List<Autor> findAll() {
		return autorRepository.findAll();
	}
	
	public Autor findByNome(String nome) {
		return autorRepository.findByNome(nome);
	}

	public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

	//Deleta por nome
	@Transactional
    public void deleteByNome(String nome) {
        autorRepository.deleteByNome(nome);
    }
	
}
