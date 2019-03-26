package com.book.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.acl.Acl;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.book.config.security.permission.AclCheck;
import com.book.config.security.permission.AclException;
import com.book.config.security.permission.Permission;
import com.book.util.AppBase;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.book.entity.Book;
import com.book.repository.BookServiceRepo;

@Controller
@RequestMapping("/admin/book")
public class BookController extends AppBase {

	@Autowired
	BookServiceRepo bookService;
    public BookController (){
        super.setInstance(this);
    }
	@RequestMapping(value = "/addBook", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.ADD_BOOK})
    public String addBook(Model model) throws AclException {
        doAclCheck("addBook", Model.class);
		Book book = new Book();
		model.addAttribute("book", book);
		return "admin/addBook";
	}

    @AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.ADD_BOOK})
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBookPost(@ModelAttribute("book") Book book, HttpServletRequest httpServletRequest) throws AclException {
        doAclCheck("addBookPost", Book.class, HttpServletRequest.class);
		bookService.save(book);
		MultipartFile bookImage = book.getBookImage();

		try {
			byte[] bytes = bookImage.getBytes();
			String name = book.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:bookList";
	}

    @AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.VIEW_BOOKS})
	@RequestMapping(value = "/bookList", produces = MediaType.APPLICATION_JSON_VALUE)
	public String bookList(Model model) throws AclException {
        doAclCheck("bookList", Model.class);
        List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList" , bookList);
		return "admin/bookList";
	}
}
