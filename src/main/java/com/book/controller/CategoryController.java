package com.book.controller;

import com.book.config.security.permission.AclCheck;
import com.book.config.security.permission.AclException;
import com.book.config.security.permission.Permission;
import com.book.entity.*;
import com.book.impl.UserServiceImpl;
import com.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController extends ControllerBase {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserServiceImpl userService;

    public CategoryController (){
        super.setInstance(this);
    }


    @RequestMapping(value = "/addCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.ADD_CATEGORY})
    public String addCategory(Model model) throws AclException {
        if (LOGGED_IN_USER == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("addCategory", Model.class);
        Category category= new Category();
        model.addAttribute("category", category);
        return "admin/addCategory";
    }

    @AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.ADD_CATEGORY})
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategoryPost(@ModelAttribute("category") Category category) throws AclException {
        if (LOGGED_IN_USER == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("addCategoryPost", Category.class);
        category.setCategoryId(System.currentTimeMillis());
        categoryService.create(category);

        return "redirect:/admin/category/categoryList";
    }

    @AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.VIEW_CATEGORY})
    @RequestMapping(value = "/categoryList", produces = MediaType.APPLICATION_JSON_VALUE)
    public String categoryList(Model model) throws AclException {
        if (LOGGED_IN_USER == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("categoryList", Model.class);
        List<Category> categoryList= categoryService.getAll();
        model.addAttribute("categoryList" , categoryList);
        User user = userService.findByUsername("mainul35");
        model.addAttribute("createdOn", user.getCreatedOn());
        model.addAttribute("createdBy", user.getCreatedBy());
        return "admin/categoryList";
    }

    public List<Category> getAllCategories () {
        return categoryService.getAll();
    }

    @Override
    public List<DomainBase> getAll() {
        return null;
    }

    @Override
    public void Save(DomainBase object) {

    }

    @Override
    public DomainBase getById(Long id) {
        return null;
    }

    @Override
    public DomainBase update(DomainBase object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
