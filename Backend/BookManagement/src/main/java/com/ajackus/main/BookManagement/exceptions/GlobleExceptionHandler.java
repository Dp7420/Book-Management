package com.ajackus.main.BookManagement.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleExceptionHandler {

	@ExceptionHandler(BookDetailsNotFound.class)
	public ResponseEntity<Map<String, Object>> handleBookDetailsNotFoundException(BookDetailsNotFound e) {
		Map<String, Object> res = new HashMap<>();
		res.put("timestamp", LocalDateTime.now());
		res.put("message", e.getMessage());
		res.put("status", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AlreadyExistsBookIdException.class)
	public ResponseEntity<Map<String, Object>> handleAlreadyExistsBookIdException(AlreadyExistsBookIdException e) {
		Map<String, Object> res = new HashMap<>();
		res.put("timestamp", LocalDateTime.now());
		res.put("message", e.getMessage());
		res.put("status", HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(res, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("timestamp", LocalDateTime.now());
		errors.put("status", HttpStatus.BAD_REQUEST.value());

		Map<String, String> validationErrors = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			validationErrors.put(error.getField(), error.getDefaultMessage());
		}
		errors.put("errors", validationErrors);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleMethodNotSupportedException(
			HttpRequestMethodNotSupportedException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", "Method Not Allowed");
		errorResponse.put("message", ex.getMessage());
		errorResponse.put("status", String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));

		return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", "Internal Server Error");
		errorResponse.put("message", ex.getMessage());
		errorResponse.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
