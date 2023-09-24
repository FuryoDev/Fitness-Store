package com.eafc.springbootbackend.controllers.administrator;

import com.eafc.springbootbackend.entities.product.SubCategory;
import com.eafc.springbootbackend.entities.shopping.PaymentMethod;
import com.eafc.springbootbackend.services.shopping.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminPaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public AdminPaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("getPaymentMethodById")
    public PaymentMethod getPaymentMethodById(@RequestParam("paymentMethodId") Long paymentMethodId) {
        return this.paymentMethodService.getPaymentMethodById(paymentMethodId);
    }

    @GetMapping("getAllPaymentMethods")
    public Collection<PaymentMethod> getAllPaymentMethods() {
        return this.paymentMethodService.getAllPaymentMethods();
    }

    @PostMapping(value = "savePaymentMethod", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void savePaymentMethod(@RequestPart("paymentMethod") PaymentMethod paymentMethod, @RequestPart("image") MultipartFile image) throws IOException {
        this.paymentMethodService.savePaymentMethod(paymentMethod, image);
    }
}
