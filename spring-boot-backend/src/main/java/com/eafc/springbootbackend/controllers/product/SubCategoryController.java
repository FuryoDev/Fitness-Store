package com.eafc.springbootbackend.controllers.product;

import com.eafc.springbootbackend.entities.product.SubCategory;
import com.eafc.springbootbackend.services.product.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

//    @GetMapping("getSubCategoryById")
    public SubCategory getSubCategoryById(@RequestParam("id") Long subCategoryId) {
        return subCategoryService.findSubCategoryById(subCategoryId).get();
    }

//    @GetMapping("getAllSuCategories")
    public Collection<SubCategory> getAllSubCategories() {
        return subCategoryService.findAllSubCategories();
    }
}
