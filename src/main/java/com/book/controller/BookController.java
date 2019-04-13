package com.book.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.book.config.security.permission.AclCheck;
import com.book.config.security.permission.AclException;
import com.book.config.security.permission.Permission;
import com.book.entity.Attachment;
import com.book.entity.DomainBase;
import com.book.entity.User;
import com.book.service.BookService;
import com.book.impl.UserServiceImpl;
import com.book.service.AttachmentService;
import com.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.book.entity.Book;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/book")
public class BookController extends ControllerBase {

	@Autowired
    BookService bookService;
	@Autowired
    UserServiceImpl userService;
	@Autowired
    CategoryService categoryService;
	@Autowired
    AttachmentService attachmentService;
    public BookController (){
        super.setInstance(this);
    }
	@RequestMapping(value = "/addBook", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.ADD_BOOK})
    public String addBook(Model model, HttpServletRequest request) throws AclException {
        if (loggedInUser() == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("addBook", Model.class, HttpServletRequest.class);
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("author", book.getAuthors() == null ? "" : book.getAuthors());
		if (request.getHeader("x-requested-with") == null) {
		    model.addAttribute("requestPath", "/admin/book/addBook");
		    return "admin/dashboard";
        }
		return "admin/book/addBook";
	}

    @AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.ADD_BOOK})
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBookPost(@ModelAttribute("book") Book book, @RequestParam(name = "image") MultipartFile file, Model model, HttpServletRequest request) throws AclException {
        if (loggedInUser() == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("addBookPost", Book.class, MultipartFile.class, Model.class, HttpServletRequest.class);

        Attachment attachment = new Attachment();
        attachment = attachmentService.save(attachment, file, book.getId());
        book.setPhoto(attachment);
		bookService.save(book);
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList" , bookList);
        User user = loggedInUser();
        model.addAttribute("createdOn", user.getCreatedOn());
        model.addAttribute("createdBy", user.getCreatedBy());
        if (request.getHeader("x-requested-with") == null) {
            model.addAttribute("requestPath", "/admin/book/bookList");
            return "admin/dashboard";
        }
        return "admin/book/bookList";
	}

    @AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.VIEW_BOOKS})
	@RequestMapping(value = "/bookList", produces = MediaType.APPLICATION_JSON_VALUE)
	public String bookList(Model model, HttpServletRequest request) throws AclException {
        if (loggedInUser() == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("bookList", Model.class, HttpServletRequest.class);
        List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList" , bookList);
        User user = loggedInUser();
		model.addAttribute("createdOn", user.getCreatedOn());
		model.addAttribute("createdBy", user.getCreatedBy());
        if (request.getHeader("x-requested-with") == null) {
            model.addAttribute("requestPath", "/admin/book/bookList");
            return "admin/dashboard";
        }
		return "admin/book/bookList";
	}

    @Override
    public List<DomainBase> getAll() {
        return null;
    }

    @Override
    public void save(DomainBase object) {

    }

    @Override
    @GetMapping(value = "/details/{id}")
    public DomainBase details (@PathVariable("id") Long id) {
        return bookService.getBook(id);
    }

    @Override
    public DomainBase update(DomainBase object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
