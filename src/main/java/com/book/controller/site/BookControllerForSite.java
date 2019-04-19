package com.book.controller.site;

import com.book.controller.ControllerBase;
import com.book.entity.productInfo.Book;
import com.book.entity.DomainBase;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/site")
public class BookControllerForSite extends ControllerBase {
    @Autowired
    private BookService bookService;
    @GetMapping(value = "/books")
    @ResponseBody public List<Book> books (HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = new HashMap<>();
        params.put("object", "ProductDisplay");
        params.put("hasParam", "false");
        return (List<Book>) this.checkRequestTypeAndPerformAction(request, response, bookService.findAll(), params);
    }

    @GetMapping(value = "/books/{id}")
    @ResponseBody public Book book(@PathVariable(name = "id") String id, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = new HashMap<>();
        params.put("object", "ProductDetails");
        params.put("hasParam", "true");
        Book book = bookService.getBook(Long.parseLong(id));
        return (Book) this.checkRequestTypeAndPerformAction(request, response, book, params);
    }

    @Override
    public List<DomainBase> getAll() {
        return null;
    }

    @Override
    public void save(DomainBase object) {

    }

    @Override
    public DomainBase details(Long id) {
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
