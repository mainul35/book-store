package com.book.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.book.config.security.permission.AclCheck;
import com.book.config.security.permission.AclException;
import com.book.config.security.permission.Permission;
import com.book.entity.Attachment;
import com.book.entity.DomainBase;
import com.book.entity.User;
import com.book.impl.UserServiceImpl;
import com.book.service.AttachmentService;
import com.book.service.CategoryService;
import com.book.util.AppBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book.entity.Book;
import com.book.repository.BookServiceRepo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/book")
public class BookController extends ControllerBase {

	@Autowired
	BookServiceRepo bookService;
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
    public String addBook(Model model) throws AclException {
        if (loggedInUser() == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("addBook", Model.class);
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("author", book.getAuthors() == null ? "" : book.getAuthors());
		return "admin/addBook";
	}

    @AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.ADD_BOOK})
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBookPost(@ModelAttribute("book") Book book, HttpServletRequest httpServletRequest, @RequestParam(name = "image") MultipartFile file) throws AclException {
        if (loggedInUser() == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("addBookPost", Book.class, HttpServletRequest.class, MultipartFile.class);
        Attachment attachment = new Attachment();
        attachment = attachmentService.save(attachment, file, book.getId());
        book.setPhoto(attachment);
		bookService.save(book);

		return "redirect:bookList";
	}

    @AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.VIEW_BOOKS})
	@RequestMapping(value = "/bookList", produces = MediaType.APPLICATION_JSON_VALUE)
	public String bookList(Model model) throws AclException {
        if (loggedInUser() == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("bookList", Model.class);
        List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList" , bookList);
        User user = userService.findByUsername("mainul35");
		model.addAttribute("createdOn", user.getCreatedOn());
		model.addAttribute("createdBy", user.getCreatedBy());
		return "admin/bookList";
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
