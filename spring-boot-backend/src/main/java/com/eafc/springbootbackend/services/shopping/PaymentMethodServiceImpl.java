package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.shopping.PaymentMethod;
import com.eafc.springbootbackend.exceptions.InvalidNameException;
import com.eafc.springbootbackend.repositories.shopping.PaymentMethodRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private String uploadPath = "C:\\Users\\mouji\\IdeaProjects\\Fitness-store-2022\\angular-frontend\\src\\assets\\images\\paymentMethods";

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public Collection<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public void savePaymentMethod(PaymentMethod paymentMethod, MultipartFile image) throws IOException {
        if(paymentMethod.getPaymentMethodId() == null && !isNameAlreadyInDb(paymentMethod.getName())){
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
                String paymentMethodName = paymentMethod.getName();

                // Create a folder with the name of the payment method
                Path paymentMethodDir = uploadDir.resolve(paymentMethodName);
                if (!Files.exists(paymentMethodDir)) {
                    Files.createDirectory(paymentMethodDir);
                }

                // Check if the image for the payment method already exists
                Path existingImage = paymentMethodDir.resolve(paymentMethodName + "." + extension);
                if (Files.exists(existingImage)) {
                    // Handle the case when the image already exists
                    // You can choose to skip saving the image or perform some other action
                    // For example, you can throw an exception or generate a unique filename
                    // based on a timestamp or a random number.
                    throw new IOException("Image already exists for the payment method: " + paymentMethodName);
                }

                // Save the uploaded file to the payment method directory
                Path filePath = paymentMethodDir.resolve(paymentMethodName + "." + extension);
                Files.copy(image.getInputStream(), filePath);

                // Return the URL of the saved image
                String imageUrl = "/assets/images/paymentMethods/" + paymentMethodName + "/" + paymentMethodName + "." + extension;

                paymentMethod.setIconURL(imageUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
            paymentMethodRepository.save(paymentMethod);
        }
        else {
            throw new InvalidNameException("A payment method with name "+ paymentMethod.getName() + " already eists");
        }
    }

    private boolean isNameAlreadyInDb(String productName) {
        return paymentMethodRepository.existsByName(productName);
    }
}
