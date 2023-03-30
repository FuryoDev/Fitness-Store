package com.eafc.springbootbackend.controllers.product;

import com.eafc.springbootbackend.entities.product.Discount;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.services.product.DiscountService;
import com.eafc.springbootbackend.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProductInfoController {

    private final ProductService productService;

    public ProductInfoController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get-all-products")
    private Collection<ProductInfo> getAllProduct() {
        return productService.findAllProducts();
    }

    @GetMapping("get-product")
    private ProductInfo getProductById(Long productId){
        return productService.findProductById(productId);
    }

    @GetMapping("get-latest-products")
    private Collection<ProductInfo> getLatestProducts() {
        return productService.findLatestProducts();
    }
    @GetMapping("get-discounted-products")
    private Collection<ProductInfo> getDiscountedProducts() {
        return  productService.findDiscountedProducts();
    }
}
