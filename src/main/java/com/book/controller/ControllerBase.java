package com.book.controller;

import com.book.entity.DomainBase;
import com.book.util.AppBase;

import java.util.List;

public abstract class ControllerBase extends AppBase {
    public abstract List<DomainBase> getAll();
    public abstract void save(DomainBase object);
    public abstract DomainBase details (Long id);
    public abstract DomainBase update (DomainBase object);
    public abstract void delete (Long id);
}
