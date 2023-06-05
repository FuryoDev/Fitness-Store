package com.eafc.springbootbackend.controllers.administrator;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.services.product.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
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

    @PostMapping(value = "saveCategory", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void saveCategory(@RequestPart("category") Category category, @RequestPart("image") MultipartFile image) throws IOException {
        categoryService.saveCategory(category, image);
    }

    @DeleteMapping(value = "deleteCategory")
    public void deleteCategory(@RequestParam("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
