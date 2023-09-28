package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.entities.shopping.CartItem;
import com.eafc.springbootbackend.repositories.shopping.CartItemRepository;
import com.eafc.springbootbackend.repositories.shopping.CartRepository;
import com.eafc.springbootbackend.services.shopping.CartService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    private final CartService cartService;
    public CartItemServiceImpl(CartItemRepository cartItemRepository, CartService cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartRepository;
    }


    @Override
    public Collection<CartItem> findCartItemsByCart(Long cartId) {
        Cart cart = cartService.findCartById(cartId).get();
        return cartItemRepository.findCartItemsByCart(cart);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }
}
