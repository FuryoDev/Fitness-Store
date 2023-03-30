package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.entities.shopping.CartItem;
import com.eafc.springbootbackend.repositories.shopping.CartItemRepository;
import com.eafc.springbootbackend.repositories.shopping.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Optional<Cart> findCartById(Long cartId) {
        return cartRepository.findById(cartId);
    }

    @Override
    public Optional<Cart> findCartByCustomer(AccountInfo customer) {
        return cartRepository.findCartByAccountInfo(customer);
    }

    @Override
    public void addCartItemToCart(CartItem cartItem, AccountInfo customer) {
        Optional<Cart> optionalCart = findCartByCustomer(customer);
        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
            CartItem ci= findItemInCart(cart.getCartItems(), cartItem).get();
            //TODO: Something different needs to be done about this null check
            if(ci != null) {
                int quantity = ci.getQuantity() + cartItem.getQuantity();
                ci.setQuantity(quantity);
            }
            else {
                ci = cartItem;
                ci.setCart(cart);
                cart.getCartItems().add(ci);
            }
            cartItemRepository.save(ci);
            cartRepository.save(cart);
        }
    }

    @Override
    public Optional<CartItem> findItemInCart(Collection<CartItem> itemsInCart, CartItem newCartItem) {
        Optional<CartItem> cartItem = itemsInCart.stream().filter(
                ci -> ci.getProductInfo().getProductId() == newCartItem.getProductInfo().getProductId()
                        && ci.getSize().equals(newCartItem.getSize())).findFirst();

        return cartItem;
    }

    @Override
    public void removeCartItemFromCart(CartItem cartItem, AccountInfo customer) {
        //TODO: Clean this
        Optional<Cart> cart = findCartByCustomer(customer);
        if(cart.isPresent()) {
            cart.get().getCartItems().remove(cartItem);
            cartRepository.save(cart.get());
        }
    }

    @Override
    public void emptyCart(Cart cart) {
        cart.getCartItems().removeAll(cart.getCartItems());
        cartRepository.save(cart);
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }
}
