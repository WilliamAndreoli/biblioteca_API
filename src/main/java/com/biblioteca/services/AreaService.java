package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Area;
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
        return areaRepository.save(area);
    }

	//Deleta por nome
	@Transactional
    public void deleteByDescricao(String descricao) {
        areaRepository.deleteByDescricao(descricao);
    }
	
}
