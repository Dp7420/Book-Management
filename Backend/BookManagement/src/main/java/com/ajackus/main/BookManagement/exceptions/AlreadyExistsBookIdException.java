package com.ajackus.main.BookManagement.exceptions;

public class AlreadyExistsBookIdException extends RuntimeException {
	public AlreadyExistsBookIdException(String msg) {
		super(msg);
	}

}
