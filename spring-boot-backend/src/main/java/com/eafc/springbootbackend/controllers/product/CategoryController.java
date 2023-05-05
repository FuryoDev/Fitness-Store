package com.eafc.springbootbackend.controllers.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.services.product.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("getCategoryById")
    public Category getCategoryById(@RequestParam("id") Long categoryId) {
        return categoryService.findCategoryById(categoryId).get();
    }

    @GetMapping("getAllCategories")
    public Collection<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }
}
