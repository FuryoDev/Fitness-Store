package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.SubCategory;

import java.util.Collection;
import java.util.Optional;

public interface SubCategoryService {

    Optional<SubCategory> findSubCategoryById(Long subCategoryId);

    Collection<SubCategory> findSubCategoriesByCategory(Long categoryId);

    Collection<SubCategory> findAllSubCategories();

    void saveSubCategory(SubCategory subCategory);

    void deleteSubCategory(Long subCategoryId);
}
