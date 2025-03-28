package com.ajackus.main.BookManagement.service;

import java.util.List;

import com.ajackus.main.BookManagement.dto.BookDetailsDTO;

public interface BookDetailsService {
	BookDetailsDTO createBookDetails(BookDetailsDTO bookDetailsDT);
	BookDetailsDTO getBookDetailsByBookId(String bookId);
	List<BookDetailsDTO> getAllBookDetails();
	BookDetailsDTO getBookDetailsById(Long id);
	BookDetailsDTO updateBookDetailsById(Long id,BookDetailsDTO bookDetailsDTO);
	BookDetailsDTO updateBookDetailsByBookId(String bookId, BookDetailsDTO bookDetailsDTO);
	BookDetailsDTO deleteBookDetailsById(Long id);
	BookDetailsDTO deleteBookDetailsByBookId(String bookId);

}
