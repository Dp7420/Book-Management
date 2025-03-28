package com.ajackus.main.BookManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ajackus.main.BookManagement.entity.BookDetails;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Long>{
	Optional<BookDetails> findByBookId(String bookId);
	void deleteByBookId(String bookId);
	boolean existsByBookId(String bookId);
}
