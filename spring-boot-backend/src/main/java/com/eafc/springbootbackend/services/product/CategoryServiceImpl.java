package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.exceptions.InvalidNameException;
import com.eafc.springbootbackend.repositories.product.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private String uploadPath = "C:\\Users\\mouji\\IdeaProjects\\Fitness-store-2022\\angular-frontend\\src\\assets\\images\\categories";
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Collection<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveCategory(Category category, MultipartFile image) throws IOException {
        if(category.getCategoryId() == null && !isNameAlreadyInDb(category.getName())) {
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
                String folderName = category.getName();

                // Create a folder with the name of the category
                Path categoryDir = uploadDir.resolve(folderName);
                if (!Files.exists(categoryDir)) {
                    Files.createDirectory(categoryDir);
                }

                // Check if the image for the category already exists
                Path existingImage = categoryDir.resolve(folderName + "." + extension);
                if (Files.exists(existingImage)) {
                    // Handle the case when the image already exists
                    // You can choose to skip saving the image or perform some other action
                    // For example, you can throw an exception or generate a unique filename
                    // based on a timestamp or a random number.
                    throw new IOException("Image already exists for the category: " + folderName);
                }

                // Save the uploaded file to the category directory
                Path filePath = categoryDir.resolve(folderName + "." + extension);
                Files.copy(image.getInputStream(), filePath);

                // Return the URL of the saved image
                String imageUrl = "/assets/images/categories/" + folderName + "/" + folderName + "." + extension;

                category.setImageURL(imageUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
            categoryRepository.save(category);
        }
        else {
            throw new InvalidNameException("A category with name "+ category.getName() + " already eists");
        }

    }

    private boolean isNameAlreadyInDb(String productName) {
        return categoryRepository.existsByName(productName);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            String folderName = category.getName();
            // Delete the folder associated with the category
            try {
                Path categoryDir = Paths.get(uploadPath).resolve(folderName);
                if (Files.exists(categoryDir)) {
                    Files.walk(categoryDir)
                            .sorted(Comparator.reverseOrder())
                            .map(Path::toFile)
                            .forEach(File::delete);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Delete the category from the repository
            categoryRepository.deleteById(categoryId);
        }
    }

}
