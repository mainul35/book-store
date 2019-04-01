package com.book.service;

import com.book.entity.Category;
import com.book.repository.CategoryRepository;
import com.book.util.AppBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends AppBase {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void create (Category category) {
        categoryRepository.save(category);
    }
}
