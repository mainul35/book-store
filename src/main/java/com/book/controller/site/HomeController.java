package com.book.controller.site;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.book.config.security.permission.AclCheck;
import com.book.config.security.permission.Permission;
import com.book.controller.ControllerBase;
import com.book.entity.DomainBase;
import com.book.entity.Role;
import com.book.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.book.entity.User;
import com.book.entity.PasswordResetToken;
import com.book.impl.MailConstructor;
import com.book.util.EncryptionUtil;
import com.book.impl.UserSecurityService;
import com.book.repository.UserService;

@Controller
public class HomeController extends ControllerBase {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MailConstructor mailConstructor;
	@Autowired
	private UserService userService;
	@Autowired
	private UserSecurityService userSecurityService;
	@Autowired
	private RoleService roleService;

	public HomeController(){
		super.setInstance(this);
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String myAccount(Model model, HttpSession session) {
        String requestAuthorizer = "" + System.currentTimeMillis();
	    model.addAttribute("classActiveLogin", true);
		model.addAttribute("origin", "ROLE_USER_"+requestAuthorizer);
        session.setAttribute("requestAuthorizer", requestAuthorizer);
		return "login";
	}

    @RequestMapping("/user/dashboard")
    @AclCheck(permissionNames = {Permission.VIEW_BOOKS})
    public String login(Model model) throws Exception {
        if (loggedInUser() == null) {
            return "redirect:/";
        }
        super.doAclCheck("login", Model.class);
        model.addAttribute("classActiveLogin", true);
        return "login";
    }

	@RequestMapping("/logout")
	public String logout (HttpSession session) {
	    SecurityContextHolder.getContext().setAuthentication(null);
	    if (loggedInUser().getRole().getName().equals("ROLE_ADMIN")) {
            session.setAttribute("user", null);
            return "redirect:/admin/login";
        } else {
            session.setAttribute("user", null);
            return "redirect:/";
        }
	}

	@RequestMapping("/forgetPassword")
	public String forgetPassword(HttpServletRequest request, @ModelAttribute("email") String email, Model model) {

		model.addAttribute("classActiveForgetPassword", true);

		User user = userService.findByEmail(email);

		if (user == null) {
			model.addAttribute("emailNotExist", true);
			return "login";
		}

		String password = EncryptionUtil.randomPassword();

		String encryptedPassword = EncryptionUtil.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);

		userService.save(user);

		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		
		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		SimpleMailMessage emails = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user,
				password);

		mailSender.send(emails);

		model.addAttribute("emailSent", "true");

		return "login";
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String newUserPost(HttpServletRequest request, @ModelAttribute("email") String userEmail,
			@ModelAttribute("username") String username, Model model) throws Exception {
		model.addAttribute("classActiveNewAccount", true);
		model.addAttribute("email", userEmail);
		model.addAttribute("username", username);

		if (userService.findByUsername(username) != null) {
			model.addAttribute("usernameExists", true);

			return "login";
		}

		if (userService.findByEmail(userEmail) != null) {
			model.addAttribute("emailExists", true);

			return "login";
		}

		User user = new User();
		user.setUsername(username);
		user.setEmail(userEmail);

		String password = EncryptionUtil.randomPassword();

		String encryptedPassword = EncryptionUtil.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(roleService.getRoleByName("ROLE_USER"));
		userService.createUser(user);

		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);

		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user,
				password);

		mailSender.send(email);

		model.addAttribute("emailSent", "true");

		return "login";
	}

	@RequestMapping("/newUser")
	public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
		PasswordResetToken passToken = userService.getPasswordResetToken(token);

		if (passToken == null) {
			String message = "Invalid Token.";
			model.addAttribute("message", message);
			return "redirect:/badRequest";
		}
		else {

		User user = passToken.getUser();
		String username = user.getUsername();

		UserDetails userDetails = userSecurityService.loadUserByUsername(username);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		model.addAttribute("user", user);

		model.addAttribute("classActiveEdit", true);
		return "myProfile";
	}}

	@GetMapping("/display-products")
	public String getBooksToDisplay (Model model) {
	    return "/site/product_display";
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
