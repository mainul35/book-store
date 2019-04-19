package com.book.service;

import com.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.productInfo.Book;

import java.util.List;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book getBook(Long id) {
		return bookRepository.findById(id).get();
	}

}
