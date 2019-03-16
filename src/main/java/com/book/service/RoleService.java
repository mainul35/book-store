package com.book.service;

import com.book.entity.Role;
import com.book.entity.UserRole;
import com.book.impl.UserServiceImpl;
import com.book.repository.RoleRepository;
import com.book.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public void saveRole (Role role) {
        roleRepository.save(role);
    }

    public Role getRoleByName (String name) {
        return roleRepository.findByname(name);
    }

    public void saveUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
    }
}
