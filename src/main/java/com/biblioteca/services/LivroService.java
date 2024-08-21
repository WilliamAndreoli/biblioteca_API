package com.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Area;
import com.biblioteca.entities.Editora;
import com.biblioteca.entities.Livro;
import com.biblioteca.repositories.AreaRepository;
import com.biblioteca.repositories.EditoraRepository;
import com.biblioteca.repositories.LivroRepository;

import jakarta.transaction.Transactional;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private EditoraRepository editoraRepository;

	public List<Livro> findAll() {
		return livroRepository.findAll();
	}

	public Livro save(Livro livro) {
		Editora editora = livro.getEditora();

		Area area = livro.getArea();

		// Verifica editora
		if (editora != null) {

			if (editora.getNome() != null) {
				// Verifica se a Editora existe em banco
				Editora existingEditora = editoraRepository.findByNome(editora.getNome());

				if (existingEditora != null) {
					livro.setEditora(existingEditora);
				} else {
					editora = editoraRepository.save(editora);
					livro.setEditora(editora);
				}

			} else {
				editora = editoraRepository.save(editora);
				livro.setEditora(editora);
			}
		}

		// Verifica Area
		if (area != null) {

			if (area.getDescricao() != null) {
				// Verifica se a Editora existe em banco
				Area existingArea = areaRepository.findByDescricao(area.getDescricao());

				if (existingArea != null) {
					livro.setArea(existingArea);
				} else {
					area = areaRepository.save(area);
					livro.setArea(area);
				}

			} else {
				area = areaRepository.save(area);
				livro.setArea(area);
			}
		}

		return livroRepository.save(livro);
	}

	@Transactional
	public void deleteById(Integer id) {
		livroRepository.deleteById(id);
	}

	public Optional<Livro> findById(Integer id) {
		return livroRepository.findById(id);
	}
	
	public Livro findByTitulo(String titulo) {
		return livroRepository.findByTitulo(titulo);
	}

}
