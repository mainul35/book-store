package com.book.controller;
import com.book.impl.UserSecurityService;
import com.book.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;
	@Autowired
	UserSecurityService userSecurityService;

	@RequestMapping("/")
	public String login() {
		return "admin/login";
	}

	@RequestMapping("/logIn")
	public String logIn() {
		return "admin/index";
	}
}
