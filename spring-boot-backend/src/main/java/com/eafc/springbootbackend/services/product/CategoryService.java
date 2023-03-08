package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Category;

import java.util.Collection;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findCategoryById(int categoryId);

    Collection<Category> findAllCategories();

    void saveCategory(Category category);

    void deleteCategory(int categoryId);
}
