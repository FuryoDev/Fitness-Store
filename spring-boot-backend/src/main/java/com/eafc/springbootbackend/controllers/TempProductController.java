package com.eafc.springbootbackend.controllers;

import com.eafc.springbootbackend.entities.product.*;
import com.eafc.springbootbackend.entities.shopping.CartItem;
import com.eafc.springbootbackend.repositories.shopping.CartItemRepository;
import com.eafc.springbootbackend.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@CrossOrigin("http://localhost:4200")
public class TempProductController {

    private final CartItemRepository cartItemRepository;

    private final ProductService productService;

    public TempProductController(ProductService productService, CartItemRepository cartItemRepository) {
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping("/products")
    Collection<ProductInfo> allProducts() {
        Collection<ProductInfo> productInfoCollection = new ArrayList<>();
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(1);
        productInfo.setName("White Product");

        Discount discount = new Discount();
        discount.setId(1);
        discount.setDiscountName("Summer Discount");
        discount.setActive(true);
        discount.setPercentage(70);

        Category category = new Category();
        category.setName("Apparel");
        category.setCategoryId(1);

        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryId(1);
        subCategory.setName("T-shirts");
        subCategory.setCategory(category);



        productInfoCollection.add(productInfo);

        //subCategory.setProductInfos(productInfoCollection);
        //productInfo.setDiscount(discount);
        //discount.setDiscountedProducts(productInfoCollection);
        return productInfoCollection;
    }

    @GetMapping("/by-category")
    Collection<ProductInfo> allProductsByCategory(@RequestParam("catId") Integer id) {
        Collection<ProductInfo> productInfoCollection = new ArrayList<>();
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(1);
        productInfo.setName("White Product");

        ProductInfo productInfo2 = new ProductInfo();
        productInfo2.setProductId(2);
        productInfo2.setName("Black Product");

        Discount discount = new Discount();
        discount.setId(1);
        discount.setDiscountName("Summer Discount");
        discount.setActive(true);
        discount.setPercentage(70);

        Category category = new Category();
        category.setName("Apparel");
        category.setCategoryId(1);

        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryId(1);
        subCategory.setName("T-shirts");
        subCategory.setCategory(category);

        Stock stock = new Stock();
        stock.setProduct(productInfo);
        stock.setStockId(1);
        stock.setSize("Small");
        stock.setMaxItems(444);

        Stock stock1 = new Stock();
        stock1.setProduct(productInfo);
        stock1.setStockId(2);
        stock1.setSize("Medium");
        stock1.setMaxItems(100);

        Collection<Stock> stocks = new ArrayList<>();
        stocks.add(stock);
        stocks.add(stock1);

        productInfo.setStocks(stocks);
        productInfo2.setStocks(stocks);

        productInfoCollection.add(productInfo);
        productInfoCollection.add(productInfo2);

//        subCategory.setProductInfos(productInfoCollection);
//        productInfo.setDiscount(discount);
//        discount.setDiscountedProducts(productInfoCollection);
        return productInfoCollection;
    }

    @GetMapping("product")
    public ProductInfo getProduct(@RequestParam("prodId") Integer productId) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(1);
        productInfo.setName("White Product");

        ProductInfo productInfo2 = new ProductInfo();
        productInfo2.setProductId(2);
        productInfo2.setName("Black Product");
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);

        if(productId.equals(1)) {
//            cartItem.setProductInfo(productInfo);
//            cartItem.setTotalPrice(99);
            return productInfo;
        }
        if(productId.equals(2)) {
//            cartItem.setProductInfo(productInfo2);
//            cartItem.setTotalPrice(200);
            return productInfo2;
        }
        return null;
    }

    @GetMapping("oneStock")
    public Stock getAStock() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(1);
        productInfo.setName("Stocked Product");

        Collection<Stock> stockCollection = new ArrayList<>();

        Stock stock = new Stock();
        stock.setStockId(1);
        stock.setSize("Small");
        stock.setMaxItems(444);

        stock.setProduct(productInfo);
        productInfo.setStocks(stockCollection);
        productInfo.getStocks().add(stock);

        return stock;
    }

    @GetMapping("categories")
    public Collection<Category> allCategories() {
        Category category1 = new Category();
        Category category2 = new Category();
        Category category3 = new Category();

        category1.setCategoryId(1);
        category1.setName("Apparels");

        category2.setCategoryId(2);
        category2.setName("Accessories");

        category3.setCategoryId(3);
        category3.setName("Supplements");

        Collection<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        return categories;
    }

    @PostMapping("category")
    public Category getCategory(@RequestBody ProductInfo product) {
        Category category1 = new Category();
        category1.setCategoryId(1);
        category1.setName("Apparels");
        product.setName("NOUVEAU NOOOOOM");
        return category1;
    }

    @PostMapping("addToCart")
    public void addToCart(@RequestBody CartItem cartItem) {
//        ProductInfo productInfo = cartItem.getProductInfo();
//        productService.saveProduct(productInfo);
//        cartItemRepository.save(cartItem);
        System.out.println("OH BBOOOOOIIIII");
    }

    @GetMapping("subcategories")
    public Collection<SubCategory> getSubCategories(@RequestParam("catId") Integer categoryId) {
        if(categoryId != null) {
            Category category = new Category();
            category.setName("Apparel");
            category.setCategoryId(1);

            Collection<SubCategory> subCategories = new ArrayList<>();

            SubCategory subCategory = new SubCategory();
            subCategory.setSubCategoryId(1);
            subCategory.setName("T-shirts");
            subCategory.setCategory(category);

            SubCategory subCategory1 = new SubCategory();
            subCategory1.setSubCategoryId(2);
            subCategory1.setName("Pantalons");
            subCategory1.setCategory(category);

            subCategories.add(subCategory);
            subCategories.add(subCategory1);

            return subCategories;
        }
        return null;
    }

    @GetMapping("by-subCategory")
    public Collection<ProductInfo> getProducts(@RequestParam("subCatId") Integer id) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(1);
        productInfo.setName("BBBBBBBBBBBBBB");

        ProductInfo productInfo2 = new ProductInfo();
        productInfo2.setProductId(2);
        productInfo2.setName("AAAAAAAAAAAAA");
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);

        Collection<ProductInfo> collection = new ArrayList<>();
        collection.add(productInfo);
        collection.add(productInfo2);

        return collection;
    }
}
