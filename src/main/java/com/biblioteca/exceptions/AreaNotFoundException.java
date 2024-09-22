package com.biblioteca.exceptions;

public class AreaNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AreaNotFoundException(String message) {
        super(message);
    }
}
