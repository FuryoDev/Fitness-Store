package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.*;
import com.eafc.springbootbackend.repositories.product.CategoryRepository;
import com.eafc.springbootbackend.repositories.product.ProductRepository;
import com.eafc.springbootbackend.repositories.product.StockRepository;
import com.eafc.springbootbackend.repositories.product.SubCategoryRepository;
import com.eafc.springbootbackend.utils.ClotheSizes;
import com.eafc.springbootbackend.utils.ShoeSizes;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    private final SubCategoryRepository subCategoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, StockRepository stockRepository, SubCategoryRepository subCategoryRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public ProductInfo findProductById(Long productId) {
        //TODO: Make a proper exception
        return productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Product not found"));
    }

    @Override
    public Collection<ProductInfo> findProductsBySubCategory(SubCategory subCategory) {
        return productRepository.findProductsBySubCategory(subCategory);
    }

    @Override
    public Collection<ProductInfo> findProductsByCategory(Category category) {
        return productRepository.findProductsByCategory(category);
    }

    @Override
    public Collection<ProductInfo> findProductsByDiscount(Discount discount) {
        return productRepository.findProductsByDiscount(discount);
    }

    @Override
    public Collection<ProductInfo> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Collection<ProductInfo> findLatestProducts() {
        return productRepository.findLatestProducts();
    }

    @Override
    public Collection<ProductInfo> findDiscountedProducts() {
        return productRepository.findDiscountedProducts();
    }

    @Override
    public ProductInfo createStockAndDate(ProductInfo newProduct) {
        //Check what kind of product
        Category category = newProduct.getSubCategory().getCategory();
        String sizingType = category.getSizingType();
        if (sizingType.equals("Clothing")) {
            for (ClotheSizes size : ClotheSizes.values()) {
                Stock newStock = new Stock();
                newStock.setSize(size.name());
                newStock.setMaxItems(100);
                newStock.setItemsInStock(100);
                newStock.setProduct(newProduct);
                stockRepository.save(newStock);
            }
        } else if (sizingType.equals("Shoe")) {
            for (ShoeSizes size : ShoeSizes.values()) {
                Stock newStock = new Stock();
                newStock.setSize(size.name());
                newStock.setMaxItems(100);
                newStock.setItemsInStock(100);
                stockRepository.save(newStock);
            }
        }
        else {
            Stock uniqueStock = new Stock();
            uniqueStock.setItemsInStock(100);
            uniqueStock.setMaxItems(100);
            uniqueStock.setSize("None");
        }
        //If not, create a single stock and set it as (UNIQUE or whatever)
        return newProduct;
    }

    @Override
    public void saveProduct(ProductInfo productInfo) {
        //TODO: clean this up, we don't nee the returned product
        Calendar today = Calendar.getInstance();
        productInfo.setCreationDate(today);
        productRepository.save(productInfo);
        productInfo = createStockAndDate(productInfo);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
