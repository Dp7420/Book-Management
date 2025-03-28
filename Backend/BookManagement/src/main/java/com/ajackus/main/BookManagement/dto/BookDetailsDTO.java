package com.ajackus.main.BookManagement.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BookDetailsDTO {
	private Long id;

	@Column(nullable = false, unique = true)
	private String bookId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String author;

	@Column(nullable = false)
	private String genre;

	@Column(nullable = false)
	private String availabilityStatus;
}
