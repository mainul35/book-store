package com.book.controller;
import com.book.config.security.permission.AclCheck;
import com.book.config.security.permission.AclException;
import com.book.config.security.permission.Permission;
import com.book.entity.DomainBase;
import com.book.impl.UserSecurityService;
import com.book.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends ControllerBase {

	@Autowired
	UserService userService;
	@Autowired
	UserSecurityService userSecurityService;

	public AdminController(){
		super.setInstance(this);
	}
	@RequestMapping("/login")
	public String login(Model model, HttpSession session) {
	    String requestAuthorizer = "" + System.currentTimeMillis();
        model.addAttribute("origin", "ROLE_ADMIN_"+requestAuthorizer);
        session.setAttribute("requestAuthorizer", requestAuthorizer);
		return "admin/login";
	}

	@AclCheck(permissionNames = {Permission.ADMIN_ONLY})
	@RequestMapping("/dashboard")
	public String logIn() throws AclException {
	    if (loggedInUser() == null) {
	        return "redirect:/admin/login";
        }
        super.doAclCheck("logIn");
		return "admin/dashboard";
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
