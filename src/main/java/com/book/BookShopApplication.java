package com.book;

import com.book.impl.UserServiceImpl;
import com.book.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.book.entity.User;
import com.book.entity.Role;
import com.book.entity.UserRole;
import com.book.impl.SecurityUtility;
import com.book.repository.UserService;

import java.util.ArrayList;
import java.util.List;

@ComponentScan(basePackages = {
        "com.book.config.security",
        "com.book.controller",
		"com.book.entity",
		"com.book.service",
		"com.book.impl",
        "com.book.repository"
        })
@SpringBootApplication
public class BookShopApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	@Autowired
    private RoleService roleService;

    public static void main(String[] args) {
		SpringApplication.run(BookShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        initializer();
	}

	private void initializer () throws Exception {
		// Creating Roles
		Role role1 = new Role();
		role1.setName("ROLE_USER");
		roleService.saveRole(role1);

        role1 = new Role();
        role1.setName("ROLE_ADMIN");
        roleService.saveRole(role1);

        // Creating Users
		User user1 = new User();
		user1.setFirstName("Jahadul");
		user1.setLastName("Rakib");
		user1.setUsername("jahadul_rakib");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("secret"));
		user1.setEmail("rakibdiu2015@gmail.com");
		List<UserRole> userRoles = new ArrayList<>();
		UserRole userRole1 = new UserRole(user1, roleService.getRoleByName("ROLE_USER"));
		roleService.saveUserRole(userRole1);
		userRoles.add(userRole1);
		UserRole userRole2 = new UserRole(user1, roleService.getRoleByName("ROLE_ADMIN"));
        userRoles.add(userRole2);
        roleService.saveUserRole(userRole2);
		userService.createUser(user1);

        user1 = new User();
        user1.setFirstName("Syed Mainul");
        user1.setLastName("Hasan");
        user1.setUsername("mainul35");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("secret"));
        user1.setEmail("mainuls18@gmail.com");
        userRoles = new ArrayList<>();
        userRole1 = new UserRole(user1, roleService.getRoleByName("ROLE_USER"));
        roleService.saveUserRole(userRole1);
        userRoles.add(userRole1);
        userRole2 = new UserRole(user1, roleService.getRoleByName("ROLE_ADMIN"));
        userRoles.add(userRole2);
        roleService.saveUserRole(userRole2);
        userService.createUser(user1);
	}
}
