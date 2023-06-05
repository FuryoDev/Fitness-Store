package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.entities.shopping.CartItem;

import java.util.Collection;

public interface CartItemService {

    Collection<CartItem> findCartItemsByCart(Long cartId);

    void deleteCartItem(Long cartItemId);
}
