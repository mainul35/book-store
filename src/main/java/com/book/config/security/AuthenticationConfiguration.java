package com.book.config.security;

import com.book.util.AppBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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