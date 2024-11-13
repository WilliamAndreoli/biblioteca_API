package com.biblioteca.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Area;
import com.biblioteca.entities.Autor;
import com.biblioteca.entities.Editora;
import com.biblioteca.entities.Livro;
import com.biblioteca.entities.Status;
import com.biblioteca.exceptions.AreaNotFoundException;
import com.biblioteca.exceptions.AutorNotFoundException;
import com.biblioteca.exceptions.EditoraNotFoundException;
import com.biblioteca.exceptions.LivroErrorException;
import com.biblioteca.exceptions.LivroNotFoundException;
import com.biblioteca.repositories.AreaRepository;
import com.biblioteca.repositories.AutorRepository;
import com.biblioteca.repositories.EditoraRepository;
import com.biblioteca.repositories.LivroRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private EditoraRepository editoraRepository;

	@Autowired
	private AutorRepository autorRepository;

	public List<Livro> findAll() {
		return livroRepository.findAll();
	}
	
	public List<Livro> findLivrosByAutor(String nomeAutor) {
        return livroRepository.findByAutorNome(nomeAutor);
    }
	
	public List<Livro> findLivrosByArea(String descricaoArea) {
        return livroRepository.findByAreaDescricao(descricaoArea);
    }
	
	public List<Livro> findLivrosByEditora(String nomeEditora) {
        return livroRepository.findByEditoraNome(nomeEditora);
    }

	public Livro save(Livro livro) throws EntityNotFoundException {
		Editora editora = livro.getEditora();

		Area area = livro.getArea();

		Set<Autor> autores = livro.getAutores();
		
		Optional<Livro> verificaLivro = livroRepository.findByTitulo(livro.getTitulo());
		
		if (verificaLivro.isPresent()) {
			throw new LivroErrorException("Já existe um livro cadastrado com esse Título");
		}
	
		// Verifica editora
		if (editora != null) {
			if (editora.getNome() != null) {
				Editora existingEditora = editoraRepository.findByNome(editora.getNome());
				if (existingEditora != null) {
					livro.setEditora(existingEditora);
				} else {
					throw new EditoraNotFoundException("Editora não encontrada com nome: " + editora.getNome());
				}
			}
		} else {
			throw new LivroErrorException("O nome da editora não pode ser nulo");
		}
		
		// Verifica Area
		if (area != null) {
	        if (area.getDescricao() != null) {
	            Area existingArea = areaRepository.findByDescricao(area.getDescricao());
	            if (existingArea != null) {
	                livro.setArea(existingArea);
	            } else {
	                throw new AreaNotFoundException("Área não encontrada com descrição: " + area.getDescricao());
	            }
	        }
	    } else {
            throw new LivroErrorException("A descrição da Área não pode ser nulo");
        }

		// Verifica Autores
		if (autores != null && !autores.isEmpty()) {
	        Set<Autor> autoresToSave = new HashSet<>();
	        for (Autor autor : autores) {
	            if (autor.getNome() != null) {
	                Autor existingAutor = autorRepository.findByNome(autor.getNome());
	                if (existingAutor != null) {
	                    autoresToSave.add(existingAutor);
	                } else {
	                    throw new AutorNotFoundException("Autor não encontrado com nome: " + autor.getNome());
	                }
	            } else {
	                autor = autorRepository.save(autor);
	                autoresToSave.add(autor);
	            }
	        }
	        livro.setAutores(autoresToSave);
	    } else {
	    	throw new LivroErrorException("Autores do Livro não pode ser nulo");
	    }

		return livroRepository.save(livro);
	}
	
	public Livro update(Livro livro) throws EntityNotFoundException {
		Editora editora = livro.getEditora();

		Area area = livro.getArea();

		Set<Autor> autores = livro.getAutores();
	
		// Verifica editora
		if (editora != null) {
			if (editora.getNome() != null) {
				Editora existingEditora = editoraRepository.findByNome(editora.getNome());
				if (existingEditora != null) {
					livro.setEditora(existingEditora);
				} else {
					throw new EditoraNotFoundException("Editora não encontrada com nome: " + editora.getNome());
				}
			}
		} else {
			throw new LivroErrorException("O nome da editora não pode ser nulo");
		}
		
		// Verifica Area
		if (area != null) {
	        if (area.getDescricao() != null) {
	            Area existingArea = areaRepository.findByDescricao(area.getDescricao());
	            if (existingArea != null) {
	                livro.setArea(existingArea);
	            } else {
	                throw new AreaNotFoundException("Área não encontrada com descrição: " + area.getDescricao());
	            }
	        }
	    } else {
            throw new LivroErrorException("A descrição da Área não pode ser nulo");
        }

		// Verifica Autores
		if (autores != null && !autores.isEmpty()) {
	        Set<Autor> autoresToSave = new HashSet<>();
	        for (Autor autor : autores) {
	            if (autor.getNome() != null) {
	                Autor existingAutor = autorRepository.findByNome(autor.getNome());
	                if (existingAutor != null) {
	                    autoresToSave.add(existingAutor);
	                } else {
	                    throw new AutorNotFoundException("Autor não encontrado com nome: " + autor.getNome());
	                }
	            } else {
	                autor = autorRepository.save(autor);
	                autoresToSave.add(autor);
	            }
	        }
	        livro.setAutores(autoresToSave);
	    } else {
	    	throw new LivroErrorException("Autores do Livro não pode ser nulo");
	    }

		return livroRepository.save(livro);
	}
	
	public Livro alteraStatus(Status status, String titulo) {
		Optional<Livro> livro = livroRepository.findByTitulo(titulo);
		
		Livro existingLivro = livro.get();
		
		existingLivro.setStatus(status);
		
		Livro savedLivro = livroRepository.save(existingLivro);
		
		return savedLivro;
	}

	@Transactional
	public void deleteById(Integer id) {
		livroRepository.deleteById(id);
	}

	public Optional<Livro> findById(Integer id) {
		return livroRepository.findById(id);
	}

	public Optional<Livro> findByTitulo(String titulo) {
		return livroRepository.findByTitulo(titulo);
	}

	@Transactional
	public void deleteByTitulo(String titulo) throws LivroNotFoundException {
			livroRepository.deleteByTitulo(titulo);
	}

}
