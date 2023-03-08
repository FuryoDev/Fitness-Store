package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.Discount;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.SubCategory;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {

    ProductInfo findProductById(int productId);

    Collection<ProductInfo> findProductsBySubCategory(SubCategory subCategory);

    Collection<ProductInfo> findProductsByCategory(Category category);

    Collection<ProductInfo> findProductsByDiscount(Discount discount);

    Collection<ProductInfo> findAllProducts();

    Collection<ProductInfo> findLatestProducts();

    Collection<ProductInfo> findDiscountedProducts();

    ProductInfo createStockAndDate(ProductInfo newProduct);

    void saveProduct(ProductInfo productInfo);

    void deleteProduct(int productId);

}
