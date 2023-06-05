package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.shopping.PaymentMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

public interface PaymentMethodService {

    Collection<PaymentMethod> getAllPaymentMethods();

    void savePaymentMethod(PaymentMethod paymentMethod, MultipartFile image) throws IOException;
}
