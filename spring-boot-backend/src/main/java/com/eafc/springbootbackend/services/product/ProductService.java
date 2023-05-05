package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.Discount;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.SubCategory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface ProductService {

    ProductInfo findProductById(Long productId);

    Collection<ProductInfo> findProductsBySubCategory(Long subCategoryId);

    Collection<ProductInfo> findProductsByCategory(Long categoryId);

    Collection<ProductInfo> findProductsByDiscount(Discount discount);

    Collection<ProductInfo> findAllProducts();

    Collection<ProductInfo> findLatestProducts();

    Collection<ProductInfo> findDiscountedProducts();

    ProductInfo createStockAndDate(ProductInfo newProduct);

    void saveProduct(ProductInfo productInfo, MultipartFile image) throws IOException;

    void deleteProduct(Long productId);

}
