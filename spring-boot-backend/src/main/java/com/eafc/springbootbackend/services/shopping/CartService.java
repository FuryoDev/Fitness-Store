package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.entities.shopping.CartItem;

import java.util.Collection;
import java.util.Optional;

public interface CartService {

    Optional<Cart> findCartById(int cartId);

    Optional<Cart> findCartByCustomer(AccountInfo customer);

    void addCartItemToCart(CartItem cartItem, AccountInfo customer);

    Optional<CartItem> findItemInCart(Collection<CartItem> itemsInCart, CartItem newCartItem);

    void removeCartItemFromCart(CartItem cartItem, AccountInfo customer);
    void emptyCart(Cart cart);

    void saveCart(Cart cart);


}
