package com.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Livro;
import com.biblioteca.repositories.LivroRepository;

import jakarta.transaction.Transactional;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	public List<Livro> findAll() {
        return livroRepository.findAll();
    }
	
	/*
	public Livro save(Livro livro) {
		return livroRepository.save(livro);
    }*/

	@Transactional
    public void deleteById(Integer id) {
        livroRepository.deleteById(id);
    }

	public Optional<Livro> findById(Integer id) {
        return livroRepository.findById(id);
    }
	
}
