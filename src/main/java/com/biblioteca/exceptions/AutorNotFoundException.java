package com.biblioteca.exceptions;

public class AutorNotFoundException extends RuntimeException {
	public AutorNotFoundException(String message) {
        super(message);
    }
}
