package com.eafc.springbootbackend.repositories.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.Discount;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductInfo, Long> {

    ProductInfo findProductInfoByProductId(Long productInfoId);

    Collection<ProductInfo> findProductsBySubCategory(SubCategory subCategory);
    @Query("SELECT p FROM ProductInfo p")
    Collection<ProductInfo> findProductsByCategory(Category category);

    @Query("SELECT p from ProductInfo  p ORDER BY p.creationDate DESC")
    Collection<ProductInfo> findLatestProducts();
    @Query("SELECT p from ProductInfo  p WHERE p.discount IS NOT NULL")
    Collection<ProductInfo> findDiscountedProducts();
    @Query("SELECT p from ProductInfo  p WHERE p.discount = :discount")
    Collection<ProductInfo> findProductsByDiscount(Discount discount);
}
