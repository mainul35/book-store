package com.book.controller;

import com.book.entity.Category;
import com.book.entity.DomainBase;
import com.book.service.CategoryService;
import com.book.util.AppBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryController extends ControllerBase {

    @Autowired
    CategoryService categoryService;

    public List<Category> getAllCategories () {
        return categoryService.getAll();
    }

    @Override
    public List<DomainBase> getAll() {
        return null;
    }

    @Override
    public void Save(DomainBase object) {

    }

    @Override
    public DomainBase getById(Long id) {
        return null;
    }

    @Override
    public DomainBase update(DomainBase object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
