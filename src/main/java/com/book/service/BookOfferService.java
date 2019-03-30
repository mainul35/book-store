package com.book.service;

import com.book.repository.BookOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookOfferService {

    @Autowired
    private BookOfferRepository bookOfferRepository;
}
