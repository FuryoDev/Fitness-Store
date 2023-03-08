package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.CustomerInfo;
import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.entities.shopping.CartItem;

import java.util.Collection;
import java.util.Optional;

public interface CartService {

    Optional<Cart> findCartById(int cartId);

    Optional<Cart> findCartByCustomer(CustomerInfo customer);

    void addCartItemToCart(CartItem cartItem, CustomerInfo customer);

    Optional<CartItem> findItemInCart(Collection<CartItem> itemsInCart, CartItem newCartItem);

    void removeCartItemFromCart(CartItem cartItem, CustomerInfo customer);
    void emptyCart(Cart cart);

    void saveCart(Cart cart);


}
