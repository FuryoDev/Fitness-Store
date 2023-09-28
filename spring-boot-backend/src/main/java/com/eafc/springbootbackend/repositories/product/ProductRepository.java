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

    boolean existsByName(String productName);

    Collection<ProductInfo> findProductsBySubCategory(SubCategory subCategory);
    @Query("SELECT p FROM ProductInfo p WHERE p.subCategory.category = :category")
    Collection<ProductInfo> findProductsByCategory(Category category);

    @Query("SELECT p FROM ProductInfo p ORDER BY p.creationDate DESC")
    Collection<ProductInfo> findLatestProducts();

    @Query("SELECT p FROM ProductInfo p WHERE p.productId IN (SELECT s.product.productId FROM Stock s WHERE s.itemsInStock > 0 GROUP BY s.product.productId ORDER BY SUM(s.itemsInStock) ASC)")
    Collection<ProductInfo> findLowStockProduct();

//    @Query("SELECT d.productsInDiscount FROM Discount d WHERE d.isActive = true ORDER BY d.discountId DESC")
//    Collection<ProductInfo> findDiscountedProduct();

}
