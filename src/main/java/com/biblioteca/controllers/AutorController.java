package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entities.Autor;
import com.biblioteca.services.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@GetMapping
	public List<Autor> getAllAutores() {
		return autorService.findAll();
	}
	
}
