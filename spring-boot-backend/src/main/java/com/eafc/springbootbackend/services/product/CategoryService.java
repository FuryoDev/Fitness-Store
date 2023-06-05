package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findCategoryById(Long categoryId);

    Collection<Category> findAllCategories();

    void saveCategory(Category category, MultipartFile image) throws IOException;

    void deleteCategory(Long categoryId);
}
