package com.book.entity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "role_uuid")
	private Long roleId;
	@Column(unique = true, nullable = false)
	private String name;
    public Role () {
        this.roleId = System.currentTimeMillis();
    }
	public Long getRoleId() {
		return roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Override
    public String getAuthority() {
        return this.name;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
