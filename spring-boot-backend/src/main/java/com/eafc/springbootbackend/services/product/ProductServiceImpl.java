package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.Stock;
import com.eafc.springbootbackend.entities.product.SubCategory;
import com.eafc.springbootbackend.exceptions.InvalidNameException;
import com.eafc.springbootbackend.repositories.product.CategoryRepository;
import com.eafc.springbootbackend.repositories.product.ProductRepository;
import com.eafc.springbootbackend.repositories.product.StockRepository;
import com.eafc.springbootbackend.repositories.product.SubCategoryRepository;
import com.eafc.springbootbackend.utils.ClotheSizes;
import com.eafc.springbootbackend.utils.ShoeSizes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private String uploadPath = "C:\\Users\\mouji\\IdeaProjects\\Fitness-store-2022\\angular-frontend\\src\\assets\\images\\products";

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    private final SubCategoryRepository subCategoryRepository;

    private final CategoryRepository categoryRepository;


    public ProductServiceImpl(ProductRepository productRepository, StockRepository stockRepository, SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductInfo findProductById(Long productId) {
        //TODO: Make a proper exception
        return productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Product not found"));
    }

    @Override
    public Collection<ProductInfo> findProductsBySubCategory(Long subCategoryId) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId).get();
        System.out.println("On peasse ici");
        return productRepository.findProductsBySubCategory(subCategory);
    }

    @Override
    public Collection<ProductInfo> findProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();

        return productRepository.findProductsByCategory(category);
    }

//    @Override
//    public Collection<ProductInfo> findProductsByDiscount(Discount discount) {
//        return productRepository.findProductsByDiscount(discount);
//    }

    @Override
    public Collection<ProductInfo> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Collection<ProductInfo> findLatestProducts() {
        return productRepository.findLatestProducts();
    }

//    @Override
//    public Collection<ProductInfo> findDiscountedProducts() {
//        return productRepository.findDiscountedProducts();
//    }

    @Override
    public void createStockAndDate(Long newProductId) {
        //Check what kind of product
        ProductInfo newProduct = productRepository.findProductInfoByProductId(newProductId);

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
                newStock.setSize(size.getValue());
                newStock.setMaxItems(100);
                newStock.setItemsInStock(100);
                newStock.setProduct(newProduct);
                stockRepository.save(newStock);
            }
        }
        else {
            Stock uniqueStock = new Stock();
            uniqueStock.setItemsInStock(100);
            uniqueStock.setMaxItems(100);
            uniqueStock.setSize("None");
            uniqueStock.setProduct(newProduct);
            stockRepository.save(uniqueStock);
        }
        //If not, create a single stock and set it as (UNIQUE or whatever)
    }

    @Override
    public void saveProduct(ProductInfo productInfo, MultipartFile image) throws IOException {
        if(productInfo.getProductId() == null && !isNameAlreadyInDb(productInfo.getName())){
            // We need to crypt the image and give it as a model to the Product
            try {
                // Check if the upload directory exists, create it if necessary
                Path uploadDir = Paths.get(uploadPath);
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                // Generate a unique filename for the uploaded file
                String originalFilename = image.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                String folderName = productInfo.getName();

                // Create a folder with the name of the product
                Path productDir = uploadDir.resolve(folderName);
                if (!Files.exists(productDir)) {
                    Files.createDirectory(productDir);
                }

                // Check if the image for the product already exists
                Path existingImage = productDir.resolve(folderName + "." + extension);
                if (Files.exists(existingImage)) {
                    // Handle the case when the image already exists
                    // You can choose to skip saving the image or perform some other action
                    // For example, you can throw an exception or generate a unique filename
                    // based on a timestamp or a random number.
                    throw new IOException("Image already exists for the product: " + folderName);
                }

                // Save the uploaded file to the product directory
                Path filePath = productDir.resolve(folderName + "." + extension);
                Files.copy(image.getInputStream(), filePath);

                // Return the URL of the saved image
                String imageUrl = "/assets/images/products/" + folderName + "/" + folderName + "." + extension;

                productInfo.setImageURL(imageUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }

            // Add the image to the Product class
            // We save it like that simply
            // When we get a Product, we need to decrypt the connected image.
            Calendar today = Calendar.getInstance();
            productInfo.setCreationDate(today);
            productInfo = productRepository.save(productInfo);
            createStockAndDate(productInfo.getProductId());
        }
        else {
            throw new InvalidNameException("A product with name "+ productInfo.getName() + " already eists");
        }
    }

    private boolean isNameAlreadyInDb(String productName) {
        return productRepository.existsByName(productName);
    }
    @Override
    public void deleteProduct(Long productId) {
        ProductInfo product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Delete the product folder
        String folderName = product.getName();
        Path productDir = Paths.get(uploadPath).resolve(folderName);
        try {
            Files.walk(productDir)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete product folder: " + folderName, e);
        }

        // Delete the product from the repository
        productRepository.deleteById(productId);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        BindingResult result = ex.getBindingResult();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

}
