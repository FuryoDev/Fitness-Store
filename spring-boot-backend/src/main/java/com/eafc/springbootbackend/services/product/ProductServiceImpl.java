package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.*;
import com.eafc.springbootbackend.repositories.product.CategoryRepository;
import com.eafc.springbootbackend.repositories.product.ProductRepository;
import com.eafc.springbootbackend.repositories.product.StockRepository;
import com.eafc.springbootbackend.repositories.product.SubCategoryRepository;
import com.eafc.springbootbackend.utils.ClotheSizes;
import com.eafc.springbootbackend.utils.ShoeSizes;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Collection;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ProductServiceImpl implements ProductService {

    private String uploadPath = "C:\\Users\\mouji\\IdeaProjects\\Fitness-store-2022\\angular-frontend\\src\\assets\\images";

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
    public void saveProduct(ProductInfo productInfo, MultipartFile image) throws IOException {
        //We need to crypt the image and give it as a model to the Product
        try {
            // Check if the upload directory exists, create it if necessary
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Generate a unique filename for the uploaded file
            String originalFilename = image.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String filename = productInfo.getName() + "." + extension;

            // Save the uploaded file to the upload directory
            Path filePath = uploadDir.resolve(filename);
            Files.copy(image.getInputStream(), filePath);

            // Return the URL of the saved image
            String imageUrl = "/assets/images/" + filename;

            productInfo.setImageURL(imageUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Add the image to Product class
        //We save it like that simplu
        //When we get a Product we need to decrypt the connected image.
        Calendar today = Calendar.getInstance();
        productInfo.setCreationDate(today);
        productRepository.save(productInfo);
        productInfo = createStockAndDate(productInfo);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
        }
        return outputStream.toByteArray();
    }
}
