package com.eafc.springbootbackend.controllers.administrator;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.services.product.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("http://localhost:4200")
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("getCategoryById")
    public Category getCategoryById(@RequestParam("catId") Long categoryId) {
        return categoryService.findCategoryById(categoryId).get();
    }

    @GetMapping("getAllCategories")
    public Collection<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @PostMapping("saveCategory")
    public void saveCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
    }
}
