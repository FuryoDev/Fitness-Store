package com.eafc.springbootbackend.controllers.shopping;

import com.eafc.springbootbackend.entities.shopping.CartItem;
import com.eafc.springbootbackend.services.shopping.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = "addToCart", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void addToCart(@RequestPart CartItem cartItem, @RequestPart String username) {
        if (username != null && !username.equals("undefined")) {
            // perform actions for authenticated user
            System.out.println("On passe ici pour la ligne panier");
            cartService.addCartItemToCart(cartItem, username);
        } else {
            System.out.println("On passe ici pour la ligne panier");
        }

    }
}
