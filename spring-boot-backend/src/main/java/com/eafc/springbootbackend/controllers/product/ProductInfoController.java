package com.eafc.springbootbackend.controllers.product;

import com.eafc.springbootbackend.entities.product.Discount;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.services.product.DiscountService;
import com.eafc.springbootbackend.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductInfoController {

    private final ProductService productService;

    public ProductInfoController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    private Collection<ProductInfo> getAllProduct() {
        return productService.findAllProducts();
    }

    @GetMapping("getProductsBySubCat")
    public Collection<ProductInfo> getProductsBySubCategory(@RequestParam("subCatId") Long subCatId) {
        return productService.findProductsBySubCategory(subCatId);
    }

    @GetMapping("getProductsByCategory")
    public Collection<ProductInfo> getProductsByCategory(@RequestParam("catId") Long categoryId) {
        return productService.findProductsByCategory(categoryId);
    }

    @GetMapping("get-product")
    private ProductInfo getProductById(@RequestParam("id") Long productId){
        return productService.findProductById(productId);
    }

    @GetMapping("get-latest-products")
    private Collection<ProductInfo> getLatestProducts() {
        return productService.findLatestProducts();
    }
//    @GetMapping("get-discounted-products")
//    private Collection<ProductInfo> getDiscountedProducts() {
//        return  productService.findDiscountedProducts();
//    }
}
