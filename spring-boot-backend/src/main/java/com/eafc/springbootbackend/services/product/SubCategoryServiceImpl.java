package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.SubCategory;
import com.eafc.springbootbackend.repositories.product.CategoryRepository;
import com.eafc.springbootbackend.repositories.product.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    private final CategoryRepository categoryRepository;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<SubCategory> findSubCategoryById(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId);
    }

    @Override
    public Collection<SubCategory> findSubCategoriesByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        Collection<SubCategory> subCategoriesByCategory = subCategoryRepository.findSubCategoriesByCategory(category);
        return subCategoriesByCategory;
    }

    @Override
    public Collection<SubCategory> findAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public void saveSubCategory(SubCategory subCategory) {
        subCategoryRepository.save(subCategory);
    }

    @Override
    public void deleteSubCategory(Long subCategoryId) {
        subCategoryRepository.deleteById(subCategoryId);
    }
}
