package com.book.repository;

import com.book.entity.BookOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOfferRepository extends JpaRepository<BookOffer, Long> {
}
