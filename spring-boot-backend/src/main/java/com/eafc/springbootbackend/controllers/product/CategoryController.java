package com.eafc.springbootbackend.controllers.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.services.product.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @GetMapping("getCategoryById")
//    public Category getCategoryById(@RequestParam("id") Integer categoryId) {
//        return categoryService.findCategoryById(categoryId).get();
//    }
//
//    @GetMapping("getAllCategories")
//    public Collection<Category> getAllCategories() {
//        return categoryService.findAllCategories();
//    }
}
