package com.book.controller;
import com.book.config.security.permission.AclCheck;
import com.book.config.security.permission.Permission;
import com.book.impl.UserSecurityService;
import com.book.repository.UserService;
import com.book.util.AppBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends AppBase {

	@Autowired
	UserService userService;
	@Autowired
	UserSecurityService userSecurityService;

	public AdminController(){
		super.setInstance(this);
	}
	@RequestMapping("/")
	public String login() {
		return "admin/login";
	}

	@AclCheck(permissionNames = {Permission.USER_ONLY})
	@RequestMapping("/logIn")
	public String logIn() throws Exception {
        super.doAclCheck("logIn");
		return "admin/index";
	}
}
