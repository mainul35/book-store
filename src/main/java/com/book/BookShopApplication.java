package com.book;

import com.book.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.book.entity.User;
import com.book.entity.Role;
import com.book.impl.SecurityUtility;
import com.book.repository.UserService;

import java.util.ArrayList;
import java.util.List;

@ComponentScan(basePackages = {
        "com.book.config.security",
        "com.book.config.beans",
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
        //Creating roles
        Role role1 = new Role();
        role1.setName("ROLE_USER");
        roleService.saveRole(role1);

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");
        roleService.saveRole(role2);

        // Creating Users
        System.out.println("Creating User 1...");
        User user1 = new User();
		user1.setFirstName("Jahadul");
		user1.setLastName("Rakib");
		user1.setUsername("jahadul_rakib");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("secret"));
		user1.setEmail("rakibdiu2015@gmail.com");
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        roles.add(roleService.getRoleByName("ROLE_USER"));
        user1.addRoles(roles);
		userService.createUser(user1);

        System.out.println("Creating User 2...");
        User user2 = new User();
        user2.setFirstName("Syed Mainul");
        user2.setLastName("Hasan");
        user2.setUsername("mainul35");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("secret"));
        user2.setEmail("mainuls18@gmail.com");
        roles = new ArrayList<>();
        roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        roles.add(roleService.getRoleByName("ROLE_USER"));
        user2.addRoles(roles);
        userService.createUser(user2);
    }
}
