package com.book.controller;

import com.book.config.security.AuthenticationConfiguration;
import com.book.entity.Role;
import com.book.entity.User;
import com.book.repository.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class CommonControl {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @RequestMapping("/login-processing")
    public void dashboard(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> params = request.getParameterMap();
        String requestAuthorizer = session.getAttribute("requestAuthorizer").toString();
        User user = userService.findByUsername(params.get("username")[0].toString());
        if (user != null) {
            if (passwordEncoder.matches(params.get("password")[0], user.getPassword())) {
                SecurityContextHolder.getContext().setAuthentication(new AuthenticationConfiguration(user));
                SecurityContextHolder.getContext().getAuthentication().setAuthenticated(true);
                if (params.get("req-origin")[0].equals(user.getRole().getName() + "_" + requestAuthorizer)) {
                    model.addAttribute("classActiveLogin", true);
                    response.sendRedirect("/"+params.get("mapping")[0].concat("/dashboard"));
                } else {
                    response.sendRedirect("403");
                }
            }
        }
    }
}
