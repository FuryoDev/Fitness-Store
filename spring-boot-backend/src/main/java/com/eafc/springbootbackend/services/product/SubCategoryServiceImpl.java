package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.SubCategory;
import com.eafc.springbootbackend.exceptions.InvalidNameException;
import com.eafc.springbootbackend.repositories.product.CategoryRepository;
import com.eafc.springbootbackend.repositories.product.SubCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private String uploadPath = "C:\\Users\\mouji\\IdeaProjects\\Fitness-store-2022\\angular-frontend\\src\\assets\\images\\subcategories";

    private final SubCategoryRepository subCategoryRepository;

    private final CategoryRepository categoryRepository;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<SubCategory> findSubCategoryById(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId);
    }

    @Override
    public Collection<SubCategory> findSubCategoriesByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        Collection<SubCategory> subCategoriesByCategory = subCategoryRepository.findSubCategoriesByCategory(category);
        return subCategoriesByCategory;
    }

    @Override
    public Collection<SubCategory> findAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public void saveSubCategory(SubCategory subCategory, MultipartFile image) throws IOException {
        if(subCategory.getSubCategoryId() == null && !isNameAlreadyInDb(subCategory.getName())){

        }
        else {
            throw new InvalidNameException("A Sub-category with name "+ subCategory.getName() + " already eists");
        }

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
            String folderName = subCategory.getName();

            // Create a folder with the name of the subcategory
            Path subCategoryDir = uploadDir.resolve(folderName);
            if (!Files.exists(subCategoryDir)) {
                Files.createDirectory(subCategoryDir);
            }

            // Check if the image for the subcategory already exists
            Path existingImage = subCategoryDir.resolve(folderName + "." + extension);
            if (Files.exists(existingImage)) {
                // Handle the case when the image already exists
                // You can choose to skip saving the image or perform some other action
                // For example, you can throw an exception or generate a unique filename
                // based on a timestamp or a random number.
                throw new IOException("Image already exists for the subcategory: " + folderName);
            }

            // Save the uploaded file to the subcategory directory
            Path filePath = subCategoryDir.resolve(folderName + "." + extension);
            Files.copy(image.getInputStream(), filePath);

            // Return the URL of the saved image
            String imageUrl = "/assets/images/subcategories/" + folderName + "/" + folderName + "." + extension;

            subCategory.setImageURL(imageUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }
        subCategoryRepository.save(subCategory);
    }

    private boolean isNameAlreadyInDb(String productName) {
        return subCategoryRepository.existsByName(productName);
    }

    @Override
    public void deleteSubCategory(Long subCategoryId) {
        Optional<SubCategory> subCategoryOptional = subCategoryRepository.findById(subCategoryId);
        if (subCategoryOptional.isPresent()) {
            SubCategory subCategory = subCategoryOptional.get();
            String folderName = subCategory.getName();
            // Delete the folder associated with the subcategory
            try {
                Path subCategoryDir = Paths.get(uploadPath).resolve(folderName);
                if (Files.exists(subCategoryDir)) {
                    Files.walk(subCategoryDir)
                            .sorted(Comparator.reverseOrder())
                            .map(Path::toFile)
                            .forEach(File::delete);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Delete the subcategory from the repository
            subCategoryRepository.deleteById(subCategoryId);
        }
    }

}
