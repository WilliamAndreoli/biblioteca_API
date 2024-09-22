package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Area;
import com.biblioteca.entities.Status;
import com.biblioteca.exceptions.AreaErrorException;
import com.biblioteca.repositories.AreaRepository;

import jakarta.transaction.Transactional;

@Service
public class AreaService {

	@Autowired
	private AreaRepository areaRepository;
	
	public List<Area> findAll() {
		return areaRepository.findAll();
	}
	
	public Area findByDescricao(String descricao) {
		return areaRepository.findByDescricao(descricao);
	}

	public Area save(Area area) {
		
		Area verificaArea = areaRepository.findByDescricao(area.getDescricao());
		
		if(verificaArea == null) {
			return areaRepository.save(area);
		} else {
			throw new AreaErrorException("Já existe uma Área cadastrada com essa descrição.");
		}
        
    }
	
	public Area alteraStatus(Status status, String descricao) {
		Area area = areaRepository.findByDescricao(descricao);
		
		area.setStatus(status);
		
		Area savedArea = areaRepository.save(area);
		
		return savedArea;
	}

	//Deleta por nome
	@Transactional
    public void deleteByDescricao(String descricao) {
        areaRepository.deleteByDescricao(descricao);
    }
	
}
