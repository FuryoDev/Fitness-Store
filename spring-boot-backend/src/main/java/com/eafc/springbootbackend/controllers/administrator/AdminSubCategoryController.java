package com.eafc.springbootbackend.controllers.administrator;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.SubCategory;
import com.eafc.springbootbackend.services.product.SubCategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminSubCategoryController {

    private final SubCategoryService subCategoryService;

    public AdminSubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("getSubCategoryById")
    public SubCategory getSubCategoryById(@RequestParam("subCatId") Long subCategoryId) {
        return subCategoryService.findSubCategoryById(subCategoryId).get();
    }

    @GetMapping("getSubCategoriesByCategory")
    public Collection<SubCategory> getSubCategoriesByCategory(@RequestParam("catId") Long categoryId) {
        return subCategoryService.findSubCategoriesByCategory(categoryId);
    }

    @GetMapping("getAllSubCategories")
    public Collection<SubCategory> getAllSubCategories() {
        return subCategoryService.findAllSubCategories();
    }

    @PostMapping(value = "saveSubCategory", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void saveSubCategory(@RequestPart("subCategory") SubCategory subCategory, @RequestPart("image") MultipartFile image) throws IOException {
        subCategoryService.saveSubCategory(subCategory, image);
    }

    @DeleteMapping(value = "deleteSubCategory")
    public void deleteSubCategory(@RequestParam("subCategoryId") Long subCategoryId) {
        subCategoryService.deleteSubCategory(subCategoryId);
    }
}
