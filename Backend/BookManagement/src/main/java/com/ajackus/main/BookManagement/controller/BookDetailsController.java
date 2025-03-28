package com.ajackus.main.BookManagement.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajackus.main.BookManagement.dto.BookDetailsDTO;
import com.ajackus.main.BookManagement.service.BookDetailsService;

@RestController
@RequestMapping("/api/book")
@Validated
public class BookDetailsController {
	
	@Autowired
	private BookDetailsService detailsService;
	
	@PostMapping
	public ResponseEntity<?> createBookDetais(@Valid @RequestBody BookDetailsDTO dto) {
		BookDetailsDTO detailsDTO = detailsService.createBookDetails(dto);
		if(detailsDTO != null) {
			return new ResponseEntity<>(detailsDTO,HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Invalid Json Data", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllBookDetails() {
		List<BookDetailsDTO> lists = detailsService.getAllBookDetails();
		if(lists !=  null) {
			return new ResponseEntity<>(lists,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBookDetailsById(@PathVariable Long id) {
		BookDetailsDTO dto = detailsService.getBookDetailsById(id);
		if(dto != null) {
			return new ResponseEntity<>(dto,HttpStatus.OK);
		}
		return new ResponseEntity<>("BookDetails Not Found with id:"+id,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/book/{bookId}")
	public ResponseEntity<?> getBookDetailsByNookId(@PathVariable String bookId) {
		BookDetailsDTO dto = detailsService.getBookDetailsByBookId(bookId);
		if(dto != null) {
			return new ResponseEntity<>(dto,HttpStatus.OK);
		}
		return new ResponseEntity<>("BookDetails Not Found with BookId:"+bookId,HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateBookDetailsById(@PathVariable @NotNull(message = "ID is required") Long id, @Valid @RequestBody BookDetailsDTO bookDetailsDTO) {
		BookDetailsDTO dto = detailsService.updateBookDetailsById(id, bookDetailsDTO);
		if(dto != null) {
			return new ResponseEntity<>(dto,HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid Json Data", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/update/book/{bookId}")
	public ResponseEntity<?> updateBookDetailsByBookId(@PathVariable String bookId, @Valid @RequestBody BookDetailsDTO bookDetailsDTO) {
		BookDetailsDTO dto = detailsService.updateBookDetailsByBookId(bookId, bookDetailsDTO);
		if(dto != null) {
			return new ResponseEntity<>(dto,HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid Json Data", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBookDetailsById(@PathVariable @NotNull(message = "ID is required") Long id) {
		BookDetailsDTO dto = detailsService.deleteBookDetailsById(id);
		if(dto != null ) {
			return new ResponseEntity<>("BookDetails Deleted Successfully.",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/delete/book/{bookId}")
	public ResponseEntity<?> deleteBookDetailsByBookId(@PathVariable @NotBlank(message = "Book ID is required") String bookId) {
		BookDetailsDTO dto = detailsService.deleteBookDetailsByBookId(bookId);
		if(dto != null ) {
			return new ResponseEntity<>("BookDetails Deleted Successfully.",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
	}
}
