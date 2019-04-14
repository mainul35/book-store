package com.book.controller.common;

import com.book.config.security.AuthenticationConfiguration;
import com.book.controller.ControllerBase;
import com.book.entity.Book;
import com.book.entity.DomainBase;
import com.book.entity.User;
import com.book.repository.UserService;
import com.book.service.AttachmentService;
import com.book.service.BookService;
import com.book.util.AppBase;
import com.book.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CommonControl extends ControllerBase {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private BookService bookService;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @RequestMapping("/login-processing")
    public void dashboard(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> params = request.getParameterMap();
        String requestAuthorizer = session.getAttribute("requestAuthorizer").toString();
        User user = userService.findByUsername(params.get("username")[0].toString());
        if (user != null) {
            if (passwordEncoder.matches(params.get("password")[0], user.getPassword())) {
                SecurityContextHolder.getContext().setAuthentication(new AuthenticationConfiguration());
                SecurityContextHolder.getContext().getAuthentication().setAuthenticated(true);
                if (params.get("req-origin")[0].equals(user.getRole().getName() + "_" + requestAuthorizer)) {
                    model.addAttribute("classActiveLogin", true);
                    setLoggedInUser(user);
                    response.sendRedirect("/"+params.get("mapping")[0].concat("/dashboard"));
                } else {
                    response.sendRedirect("403");
                }
            } else {
                if (params.get("req-origin")[0].equals(user.getRole().getName() + "_" + requestAuthorizer)) {
                    response.sendRedirect("/"+params.get("mapping")[0].concat("/login"));
                } else {
                    response.sendRedirect("/login");
                }
            }
        } else {
            if (!(params.get("mapping")[0].equals("user"))) {
                response.sendRedirect("/"+params.get("mapping")[0].concat("/login"));
            } else {
                response.sendRedirect("/login");
            }
        }
    }

    @RequestMapping("/403")
    public String _403 () {
        return "403";
    }

    @Override
    @GetMapping(value = "/book/all", produces = {"application/json"})
    @ResponseBody public List getAll() {
        List<Book> books = bookService.findAll().stream().filter(book -> book.isActive() == true).collect(Collectors.toList());
        return books;
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
