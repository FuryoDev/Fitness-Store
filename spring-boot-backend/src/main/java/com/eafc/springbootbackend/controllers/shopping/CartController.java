package com.eafc.springbootbackend.controllers.shopping;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.entities.shopping.CartItem;
import com.eafc.springbootbackend.services.customer.CustomerService;
import com.eafc.springbootbackend.services.product.CartItemService;
import com.eafc.springbootbackend.services.shopping.CartService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CartController {

    private final CustomerService customerService;
    private final CartService cartService;

    private final CartItemService cartItemService;

    public CartController(CustomerService customerService, CartService cartService, CartItemService cartItemService) {
        this.customerService = customerService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
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

    @GetMapping("retrieveCart")
    public Cart retrieveUserCart(@RequestParam String username) {
        //TODO: Clean this code (redundant)
        AccountInfo customer = customerService.findByUsername(username);
        return cartService.findCartByCustomer(customer).get();
    }

    @PutMapping("updateCartItem")
    public void updateCartItem(@RequestBody CartItem cartItem){
        cartItemService.saveCartItem(cartItem);
    }

    @DeleteMapping("deleteCartItem")
    public void deleteCartItem(@RequestParam("cartItemId") Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
    }

    //TODO: - Never send username from Frontend
    //      - Send the cart object or the CartItems Collections

    @GetMapping("retrieveCartItems")
    public Collection<CartItem> retrieveUserCartItems(@RequestParam("cartId") Long cartId) {
        return cartItemService.findCartItemsByCart(cartId);
    }

    @PostMapping("saveCart")
    public void saveCart(@RequestBody Cart cart) {
        cartService.saveCart(cart);
    }
}
