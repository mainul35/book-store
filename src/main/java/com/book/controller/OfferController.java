package com.book.controller;

import com.book.entity.DomainBase;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OfferController extends ControllerBase {
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
