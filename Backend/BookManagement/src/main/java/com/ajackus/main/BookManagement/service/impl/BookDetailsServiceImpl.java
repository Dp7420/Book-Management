package com.ajackus.main.BookManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajackus.main.BookManagement.dto.BookDetailsDTO;
import com.ajackus.main.BookManagement.entity.BookDetails;
import com.ajackus.main.BookManagement.exceptions.AlreadyExistsBookIdException;
import com.ajackus.main.BookManagement.exceptions.BookDetailsNotFound;
import com.ajackus.main.BookManagement.repository.BookDetailsRepository;
import com.ajackus.main.BookManagement.service.BookDetailsService;

@Service
public class BookDetailsServiceImpl implements BookDetailsService {

	@Autowired
	private BookDetailsRepository bookDetailsRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public BookDetailsDTO createBookDetails(BookDetailsDTO bookDetailsDTO) {
		if(bookDetailsRepository.existsByBookId(bookDetailsDTO.getBookId())) {
			throw new AlreadyExistsBookIdException("Already Book id Existsted "+ bookDetailsDTO.getBookId());
		}
		BookDetails bookDetails = mapper.map(bookDetailsDTO, BookDetails.class);
		BookDetailsDTO dto = mapper.map(bookDetailsRepository.save(bookDetails), BookDetailsDTO.class);
		return dto;
	}

	@Override
	public BookDetailsDTO getBookDetailsByBookId(String bookId) {
		BookDetails details = bookDetailsRepository.findByBookId(bookId)
				.orElseThrow(() -> new BookDetailsNotFound("BookDetails Not Found With BookId:" + bookId));
		return mapper.map(details, BookDetailsDTO.class);
	}

	@Override
	public List<BookDetailsDTO> getAllBookDetails() {
		List<BookDetails> lists = bookDetailsRepository.findAll();
		return lists.stream().map(book -> mapper.map(book, BookDetailsDTO.class)).toList();
	}

	@Override
	public BookDetailsDTO getBookDetailsById(Long id) {
		BookDetails details = bookDetailsRepository.findById(id)
				.orElseThrow(() -> new BookDetailsNotFound("BookDetails Not Found With id:" + id));
		return mapper.map(details, BookDetailsDTO.class);
	}

	@Override
	public BookDetailsDTO updateBookDetailsById(Long id, BookDetailsDTO bookDetailsDTO) {
		Optional<BookDetails> details = bookDetailsRepository.findById(id);
		if (details.isPresent()) {
			BookDetails exsitedBook = details.get();
			if (bookDetailsDTO.getAuthor() != null)
				exsitedBook.setAuthor(bookDetailsDTO.getAuthor());
			if (bookDetailsDTO.getAvailabilityStatus() != null)
				exsitedBook.setAvailabilityStatus(bookDetailsDTO.getAvailabilityStatus());
			if (bookDetailsDTO.getTitle() != null)
				exsitedBook.setTitle(bookDetailsDTO.getTitle());
			if (bookDetailsDTO.getGenre() != null)
				exsitedBook.setGenre(bookDetailsDTO.getGenre());

			return mapper.map(bookDetailsRepository.save(exsitedBook), BookDetailsDTO.class);
		}
		throw new BookDetailsNotFound("BookDetails Not Found");

	}

	@Override
	public BookDetailsDTO updateBookDetailsByBookId(String bookId, BookDetailsDTO bookDetailsDTO) {
		BookDetails details = bookDetailsRepository.findByBookId(bookId)
				.orElseThrow(() -> new BookDetailsNotFound("BookDetails Not Found With BookId:" + bookId));
		if (bookDetailsDTO.getAuthor() != null)
			details.setAuthor(bookDetailsDTO.getAuthor());
		if (bookDetailsDTO.getAvailabilityStatus() != null)
			details.setAvailabilityStatus(bookDetailsDTO.getAvailabilityStatus());
		if (bookDetailsDTO.getTitle() != null)
			details.setTitle(bookDetailsDTO.getTitle());
		if (bookDetailsDTO.getGenre() != null)
			details.setGenre(bookDetailsDTO.getGenre());
		return mapper.map(bookDetailsRepository.save(details), BookDetailsDTO.class);
	}

	@Override
	public BookDetailsDTO deleteBookDetailsById(Long id) {
		Optional<BookDetails> details = bookDetailsRepository.findById(id);
		if (details.isPresent()) {
			bookDetailsRepository.deleteById(id);
			return mapper.map(details.getClass(), BookDetailsDTO.class);
		}
		throw new BookDetailsNotFound("BookDetails Not Found with id: "+id);
	}

	@Override
	public BookDetailsDTO deleteBookDetailsByBookId(String bookId) {
		Optional<BookDetails> details = bookDetailsRepository.findByBookId(bookId);
		if (details.isPresent()) {
			bookDetailsRepository.deleteByBookId(bookId);
			return mapper.map(details.getClass(), BookDetailsDTO.class);
		}
		throw new BookDetailsNotFound("BookDetails Not Found with bookId: "+bookId);
	}

}
