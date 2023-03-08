package com.eafc.springbootbackend.controllers.administrator;

import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.services.product.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("http://localhost:4200")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("getProductById")
    public ProductInfo getProductById(@RequestParam("id") Integer id) {
        return productService.findProductById(id);
    }

    @GetMapping("getAllProducts")
    public Collection<ProductInfo> getAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping("saveProduct")
    public void saveProduct(@RequestBody ProductInfo productInfo) {
        productService.saveProduct(productInfo);
    }

    @DeleteMapping("deleteProduct")
    public void deleteProduct(@RequestParam("id") Integer id){
        productService.deleteProduct(id);
    }
}
