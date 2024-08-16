package com.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.Area;

public interface AreaRepository extends JpaRepository<Area, Integer>{
	
	Area findByDescricao(String descricao);
	
	void deleteByDescricao(String descricao);
	
}
