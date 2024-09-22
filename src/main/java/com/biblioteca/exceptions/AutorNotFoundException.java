package com.biblioteca.exceptions;

public class AutorNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AutorNotFoundException(String message) {
        super(message);
    }
}
