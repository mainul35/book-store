package com.book.repository;

import com.book.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<Role, Long> {
}
