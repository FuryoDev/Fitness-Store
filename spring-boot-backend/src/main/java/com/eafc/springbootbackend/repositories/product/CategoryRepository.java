package com.eafc.springbootbackend.repositories.product;

import com.eafc.springbootbackend.entities.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
