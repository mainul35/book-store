package com.book;

import com.book.config.security.permission.Permission;
import com.book.entity.*;
import com.book.service.CategoryService;
import com.book.service.RoleService;
import com.book.service.UserPermissionService;
import com.book.util.AppBase;
import com.book.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.book.util.EncryptionUtil;
import com.book.repository.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.*;

@ComponentScan(basePackages = {
        "com.book.config.security",
        "com.book.config.beans",
        "com.book.controller",
		"com.book.entity",
		"com.book.service",
		"com.book.impl",
        "com.book.repository",
        "com.book.util",
        })
@SpringBootApplication
public class BookShopApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	@Autowired
    private RoleService roleService;
	@Autowired
    UserPermissionService userPermissionService;
	@Autowired
    CategoryService categoryService;
	@Autowired
    ServletContext servletContext;
	@Autowired
    HttpSession httpSession;

    public static void main(String[] args) {
		SpringApplication.run(BookShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//        initializer();
        AppBase.FILE_STORAGE_BASE_DIR = servletContext.getRealPath("\\") + "temp\\";
        FileUtil.makeDirectory(AppBase.FILE_STORAGE_BASE_DIR);
        AppBase.httpSession = httpSession;


        System.out.println("============ Application is ready ===============");
	}

	private void initializer () throws Exception {
        //Creating roles
        Role role1 = new Role();
        role1.setName("ROLE_USER");
        role1.setCreatedBy("System");
        role1.setUpdatedBy("System");
        role1.setCreatedOn(new Date());
        role1.setUpdatedOn(new Date());
        roleService.saveRole(role1);

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");
        role2.setCreatedBy("System");
        role2.setUpdatedBy("System");
        role2.setCreatedOn(new Date());
        role2.setUpdatedOn(new Date());
        roleService.saveRole(role2);

        // Creating Users
        System.out.println("Creating User 1...");
        User user1 = new User();
		user1.setFirstName("Jahadul");
		user1.setLastName("Rakib");
		user1.setUsername("jahadul_rakib");
		user1.setPassword(EncryptionUtil.passwordEncoder().encode("secret"));
		user1.setEmail("rakibdiu2015@gmail.com");
        user1.setCreatedBy("System");
        user1.setUpdatedBy("System");
        user1.setCreatedOn(new Date());
        user1.setUpdatedOn(new Date());
		role2.getUsers().add(user1);
        user1.setRole(role2);
		userService.createUser(user1);

        for (Permission value : Permission.values()) {
            UserPermission userPermission = new UserPermission();
            userPermission.setUsername(user1.getUsername());
            userPermission.setPermission(value);
            userPermissionService.save(userPermission);
        }

        System.out.println("Creating User 2...");
        User user2 = new User();
        user2.setFirstName("Syed Mainul");
        user2.setLastName("Hasan");
        user2.setUsername("mainul35");
        user2.setPassword(EncryptionUtil.passwordEncoder().encode("secret"));
        user2.setEmail("mainuls18@gmail.com");
        role2.getUsers().add(user2);
        user2.setRole(role2);
        user2.setCreatedBy("System");
        user2.setUpdatedBy("System");
        user2.setCreatedOn(new Date());
        user2.setUpdatedOn(new Date());
        userService.createUser(user2);

        for (Permission value : Permission.values()) {
            if (!value.equals(Permission.USER_ONLY)) {
                UserPermission userPermission = new UserPermission();
                userPermission.setUsername(user2.getUsername());
                userPermission.setPermission(value);
                userPermissionService.save(userPermission);
            }
        }
        
        System.out.println("Creating User 3...");
        User user3 = new User();
        user3.setFirstName("Tanveer");
        user3.setLastName("Hasan");
        user3.setUsername("tanveer");
        user3.setPassword(EncryptionUtil.passwordEncoder().encode("secret"));
        user3.setEmail("tanveer@gmail.com");
        role1.getUsers().add(user3);
        user3.setCreatedBy("System");
        user3.setUpdatedBy("System");
        user3.setCreatedOn(new Date());
        user3.setUpdatedOn(new Date());
        user3.setRole(role1);
        userService.createUser(user3);

        UserPermission userPermission = new UserPermission();
        userPermission.setUsername(user3.getUsername());
        userPermission.setPermission(Permission.VIEW_BOOKS);
        userPermission.setCreatedBy("System");
        userPermission.setUpdatedBy("System");
        userPermission.setCreatedOn(new Date());
        userPermission.setUpdatedOn(new Date());
        userPermissionService.save(userPermission);

        //  Creating Categories
        Category category = new Category();
        category.setCategoryId(System.currentTimeMillis());
        category.setCategoryName("Programming");
        category.setCreatedBy("System");
        category.setCreatedOn(new Date());
        categoryService.create(category);

        category = new Category();
        category.setCategoryId(System.currentTimeMillis());
        category.setCategoryName("Engineering");
        category.setCreatedBy("System");
        category.setCreatedOn(new Date());
        categoryService.create(category);

        category = new Category();
        category.setCategoryId(System.currentTimeMillis());
        category.setCategoryName("Artificial Intelligence");
        category.setCreatedBy("System");
        category.setCreatedOn(new Date());
        categoryService.create(category);

        category = new Category();
        category.setCategoryId(System.currentTimeMillis());
        category.setCategoryName("History");
        category.setCreatedBy("System");
        category.setCreatedOn(new Date());
        categoryService.create(category);

        category = new Category();
        category.setCategoryId(System.currentTimeMillis());
        category.setCategoryName("Art");
        category.setCreatedBy("System");
        category.setCreatedOn(new Date());
        categoryService.create(category);

        // Creating Offers
        Offer offer = new Offer();
        offer.setOfferName("Opening Discount");
        offer.setOfferId(System.currentTimeMillis());

    }
}
