package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Editora;
import com.biblioteca.entities.Status;
import com.biblioteca.exceptions.EditoraErrorException;
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
		
		Editora verificaEditora = editoraRepository.findByNome(editora.getNome());
		
		if(verificaEditora == null) {
			return editoraRepository.save(editora);	
		} else {
			throw new EditoraErrorException("Já existe uma Editora cadastrada com esse nome.");
		}
		
        
    }
	
	public Editora alteraStatus(Status status, String nome) {
		Editora editora = editoraRepository.findByNome(nome);
		
		editora.setStatus(status);
		
		Editora savedEditora = editoraRepository.save(editora);
		
		return savedEditora;
	}

	//Deleta por nome
	@Transactional
    public void deleteByNome(String nome) {
        editoraRepository.deleteByNome(nome);
    }

	//Busca por nome
	public Editora findByNome(String nome) {
        return editoraRepository.findByNome(nome);
    }
	
}
