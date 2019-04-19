package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.book.entity.productInfo.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
}
