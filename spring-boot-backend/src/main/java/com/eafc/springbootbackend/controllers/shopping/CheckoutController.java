package com.eafc.springbootbackend.controllers.shopping;

import com.eafc.springbootbackend.entities.customer.ShippingAddress;
import com.eafc.springbootbackend.entities.shopping.CartItem;
import com.eafc.springbootbackend.entities.shopping.OrderInfo;
import com.eafc.springbootbackend.services.shopping.CheckOutService;
import com.eafc.springbootbackend.services.shopping.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CheckoutController {

    private final PaymentMethodService paymentMethodService;

    private final CheckOutService checkOutService;

    public CheckoutController(CheckOutService checkOutService, PaymentMethodService paymentMethodService) {
        this.checkOutService = checkOutService;
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping(value = "validateCart", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void validateCart(@RequestPart("cartItems") Collection<CartItem> cartItems, @RequestPart("orderInfo")
    OrderInfo orderInfo, @RequestPart("username") String username, @RequestPart("shippingAddress") ShippingAddress shippingAddress) {
        this.checkOutService.processCheckout(cartItems, orderInfo, username, shippingAddress);
    }
}
