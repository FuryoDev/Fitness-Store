package com.eafc.springbootbackend.controllers.administrator;

import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.SubCategory;
import com.eafc.springbootbackend.services.product.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("getProductById")
    public ProductInfo getProductById(@RequestParam("id") Long id) {
        return productService.findProductById(id);
    }

    @GetMapping("getProductsBySubCategory")
    public Collection<ProductInfo> getProductsBySubCategory(@RequestParam("subCatId") Long subCatId) {
        return productService.findProductsBySubCategory(subCatId);
    }

    @GetMapping("getAllProducts")
    public Collection<ProductInfo> getAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping(value = "saveProduct", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void saveProduct(@RequestPart("productInfo") ProductInfo productInfo, @RequestPart("image") MultipartFile image) throws IOException {
        productService.saveProduct(productInfo, image);
    }

    @DeleteMapping("deleteProduct")
    public void deleteProduct(@RequestParam("productId") Long id){
        productService.deleteProduct(id);
    }
}
