package com.stackroute.giphermanager.exception;

public class GipherDoesNotExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public GipherDoesNotExistsException(String message) {
        super(message);
    }
}
