package com.book.controller;

import javax.servlet.http.HttpServletRequest;

import com.book.impl.UserSecurityService;
import com.book.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String logIn(/*HttpServletRequest request, @ModelAttribute("username") String username,
			@ModelAttribute("password") String password, Model model*/) {

/*		UserDetails userDetails = userSecurityService.loadUserByUsername(username);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
				userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);*/

		return "admin/index";
	}
}
