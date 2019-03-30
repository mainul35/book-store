package com.book.config.security;

import com.book.entity.Role;
import com.book.entity.User;
import com.book.util.AppBase;
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

    private boolean authenticated;

    public AuthenticationConfiguration(){}

    public AuthenticationConfiguration(User user) {
        LOGGED_IN_USER = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return LOGGED_IN_USER == null ? null : LOGGED_IN_USER.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return LOGGED_IN_USER == null ? null : LOGGED_IN_USER.getPassword();
    }

    @Override
    public Object getDetails() {
        return LOGGED_IN_USER;
    }

    @Override
    public Object getPrincipal() {
        return LOGGED_IN_USER == null ? null : LOGGED_IN_USER.getUsername();
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
        return LOGGED_IN_USER == null ? null : LOGGED_IN_USER.getUsername();
    }
}