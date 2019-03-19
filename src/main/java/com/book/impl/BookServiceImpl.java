package com.book.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.repository.BookRepo;
import com.book.repository.BookServiceRepo;

@Service
public class BookServiceImpl implements BookServiceRepo {

	@Autowired
	private BookRepo repo;
	@Override
	public Book save(Book book) {
		return repo.save(book);
	}

}
