package com.book.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.book.entity.auth.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
