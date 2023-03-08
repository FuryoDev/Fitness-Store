package com.eafc.springbootbackend.repositories.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    Collection<SubCategory> findSubCategoriesByCategory(Category category);
}
