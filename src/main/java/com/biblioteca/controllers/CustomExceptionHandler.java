package com.biblioteca.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.exceptions.AreaErrorException;
import com.biblioteca.exceptions.AreaNotFoundException;
import com.biblioteca.exceptions.AutorErrorException;
import com.biblioteca.exceptions.AutorNotFoundException;
import com.biblioteca.exceptions.EditoraNotFoundException;
import com.biblioteca.exceptions.EmprestimoErrorException;
import com.biblioteca.exceptions.JWTTokenException;
import com.biblioteca.exceptions.LivroErrorException;
import com.biblioteca.exceptions.LivroNotFoundException;
import com.biblioteca.exceptions.LoginErrorException;
import com.biblioteca.exceptions.UsuarioErrorException;
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
    
    @ExceptionHandler(AreaErrorException.class)
    public ResponseEntity<Map<String, String>> handleAreaError(AreaErrorException ex) {
    	Map<String, String> response = new HashMap<>();
	    response.put("error", "Erro na Area");
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
    
    @ExceptionHandler(AutorErrorException.class)
    public ResponseEntity<Map<String, String>> handleErrorFound(AutorErrorException ex) {
    	Map<String, String> response = new HashMap<>();
	    response.put("error", "Erro no Autor");
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
    
    @ExceptionHandler(JWTTokenException.class)
    public ResponseEntity<Map<String, String>> handleLoginError(JWTTokenException ex) {
		Map<String, String> response = new HashMap<>();
	    response.put("error", "Erro no token");
	    response.put("message", ex.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(UsuarioErrorException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioError(UsuarioErrorException ex) {
		Map<String, String> response = new HashMap<>();
	    response.put("error", "Erro no Usuário");
	    response.put("message", ex.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(LivroErrorException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioError(LivroErrorException ex) {
		Map<String, String> response = new HashMap<>();
	    response.put("error", "Erro no Livro");
	    response.put("message", ex.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(EmprestimoErrorException.class)
    public ResponseEntity<Map<String, String>> handleEmprestimoError(EmprestimoErrorException ex) {
		Map<String, String> response = new HashMap<>();
	    response.put("error", "Erro no Emprestimo");
	    response.put("message", ex.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
