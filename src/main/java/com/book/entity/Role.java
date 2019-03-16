package com.book.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority {

	@Id
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
}
