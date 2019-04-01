package com.book.config.security;

import com.book.entity.Role;
import com.book.entity.User;
import com.book.util.AppBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Service
public class AuthenticationConfiguration extends AppBase implements Authentication {
    @Autowired
    private HttpSession httpSession;
    private boolean authenticated;

    public AuthenticationConfiguration(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return loggedInUser() == null ? null : loggedInUser().getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return loggedInUser() == null ? null : loggedInUser().getPassword();
    }

    @Override
    public Object getDetails() {
        return loggedInUser();
    }

    @Override
    public Object getPrincipal() {
        return loggedInUser() == null ? null : loggedInUser().getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return loggedInUser() == null ? null : loggedInUser().getUsername();
    }
}