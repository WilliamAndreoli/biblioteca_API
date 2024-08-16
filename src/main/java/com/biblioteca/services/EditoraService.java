package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Editora;
import com.biblioteca.repositories.EditoraRepository;

import jakarta.transaction.Transactional;

@Service
public class EditoraService {

	@Autowired
	private EditoraRepository editoraRepository;
	
	public List<Editora> findAll() {
		return editoraRepository.findAll();
	}
	
	public Editora save(Editora editora) {
        return editoraRepository.save(editora);
    }

	@Transactional
    public void deleteByNome(String nome) {
        editoraRepository.deleteByNome(nome);
    }

	//Busca por nome
	public List<Editora> findByNome(String nome) {
        return editoraRepository.findByNome(nome);
    }
	
}
