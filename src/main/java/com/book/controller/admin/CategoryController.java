package com.book.controller.admin;


import com.book.config.security.permission.AclCheck;
import com.book.config.security.permission.AclException;
import com.book.controller.ControllerBase;
import com.book.entity.Category;
import com.book.entity.DomainBase;
import com.book.entity.User;
import com.book.impl.UserServiceImpl;
import com.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.book.config.security.permission.Permission.*;

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
    @AclCheck(permissionNames = {ADMIN_ONLY, ADD_CATEGORY})
    public String addCategory(Model model, HttpServletRequest request) throws AclException {
        if (loggedInUser() == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("addCategory", Model.class, HttpServletRequest.class);
        Category category= new Category();
        model.addAttribute("category", category);
        if (request.getHeader("x-requested-with") == null) {
            model.addAttribute("requestPath", "/admin/category/addCategory");
            return "admin/dashboard";
        }
        return "admin/category/addCategory";
    }

    @AclCheck(permissionNames = {ADMIN_ONLY, ADD_CATEGORY})
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategoryPost(@ModelAttribute("category") Category category) throws AclException {
        if (loggedInUser() == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("addCategoryPost", Category.class);
        category.setCategoryId(System.currentTimeMillis());
        categoryService.create(category);

        return "redirect:/admin/category/categoryList";
    }

    @AclCheck(permissionNames = {ADMIN_ONLY, VIEW_CATEGORY})
    @RequestMapping(value = "/categoryList", produces = MediaType.APPLICATION_JSON_VALUE)
    public String categoryList(Model model, HttpServletRequest request) throws AclException {
        if (loggedInUser() == null) {
            return "redirect:/admin/login";
        }
        doAclCheck("categoryList", Model.class, HttpServletRequest.class);
        List<Category> categoryList= categoryService.getAll();
        model.addAttribute("categoryList" , categoryList);
        User user = userService.findByUsername("mainul35");
        model.addAttribute("createdOn", user.getCreatedOn());
        model.addAttribute("createdBy", user.getCreatedBy());
        if (request.getHeader("x-requested-with") == null) {
            model.addAttribute("requestPath", "/admin/category/categoryList");
            return "admin/dashboard";
        }
        return "admin/category/categoryList";
    }

    public List<Category> getAllCategories () {
        return categoryService.getAll();
    }

    @Override
    public List<DomainBase> getAll() {
        return null;
    }

    @Override
    public void save(DomainBase object) {

    }

    @Override
    public DomainBase details(Long id) {
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
