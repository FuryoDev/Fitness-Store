package com.eafc.springbootbackend.controllers.administrator;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.SubCategory;
import com.eafc.springbootbackend.services.product.SubCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("http://localhost:4200")
public class AdminSubCategoryController {

    private final SubCategoryService subCategoryService;

    public AdminSubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("getSubCategoryById")
    public SubCategory getSubCategoryById(@RequestParam("subCatId") Integer subCategoryId) {
        return subCategoryService.findSubCategoryById(subCategoryId).get();
    }

    @GetMapping("getSubCategoriesByCategory")
    public Collection<SubCategory> getSubCategoriesByCategory(@RequestParam("catId") Integer categoryId) {
        return subCategoryService.findSubCategoriesByCategory(categoryId);
    }

    @GetMapping("getAllSubCategories")
    public Collection<SubCategory> getAllSubCategories() {
        return subCategoryService.findAllSubCategories();
    }

    @PostMapping("saveSubCategory")
    public void saveSubCategory(@RequestBody SubCategory subCategory) {
        subCategoryService.saveSubCategory(subCategory);
    }
}
