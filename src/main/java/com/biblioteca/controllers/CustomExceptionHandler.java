package com.biblioteca.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.exceptions.AreaNotFoundException;
import com.biblioteca.exceptions.AutorNotFoundException;
import com.biblioteca.exceptions.EditoraNotFoundException;
import com.biblioteca.exceptions.LivroNotFoundException;
import com.biblioteca.exceptions.LoginErrorException;
import com.biblioteca.exceptions.UsuarioNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(EditoraNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEditoraNotFound(EditoraNotFoundException ex) {
		Map<String, String> response = new HashMap<>();
	    response.put("error", "Editora não encontrada");
	    response.put("message", ex.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AreaNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAreaNotFound(AreaNotFoundException ex) {
    	Map<String, String> response = new HashMap<>();
	    response.put("error", "Área não encontrada");
	    response.put("message", ex.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AutorNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAutorNotFound(AutorNotFoundException ex) {
    	Map<String, String> response = new HashMap<>();
	    response.put("error", "Autor não encontrado");
	    response.put("message", ex.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(LivroNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleLivroNotFound(LivroNotFoundException ex) {
		Map<String, String> response = new HashMap<>();
	    response.put("error", "Livro não encontrado");
	    response.put("message", ex.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioNotFound(UsuarioNotFoundException ex) {
		Map<String, String> response = new HashMap<>();
	    response.put("error", "Usuário não encontrado");
	    response.put("message", ex.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(LoginErrorException.class)
    public ResponseEntity<Map<String, String>> handleLoginError(LoginErrorException ex) {
		Map<String, String> response = new HashMap<>();
	    response.put("error", "Credências inválidas");
	    response.put("message", ex.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
