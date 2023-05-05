package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.entities.shopping.CartItem;
import com.eafc.springbootbackend.repositories.shopping.CartItemRepository;
import com.eafc.springbootbackend.repositories.shopping.CartRepository;
import com.eafc.springbootbackend.services.customer.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final CustomerService customerService;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.customerService = customerService;
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
    public void addCartItemToCart(CartItem cartItem, String customerUsername) {
        AccountInfo customer = customerService.findByUsername(customerUsername);
        Cart cart = findCartByCustomer(customer).get();
        //TODO: Get the product from the DB and re-associate it to the cartItem
        Collection<CartItem> cartItems = cart.getCartItems();
        CartItem ci = findItemInCart(cartItems, cartItem);
        //TODO: Something different needs to be done about this null check
        System.out.println("Petit check vite fait");
        cartRepository.save(cart);
        ci.setCart(cart);
        cartItemRepository.save(ci);
    }

    @Override
    public CartItem findItemInCart(Collection<CartItem> itemsInCart, CartItem newCartItem) {
        for (CartItem cartItem : itemsInCart) {
            if (cartItem.getProductInfo().equals(newCartItem.getProductInfo()) && cartItem.getSize().equals(newCartItem.getSize())) {
                int qty = cartItem.getQuantity() + newCartItem.getQuantity();
                cartItem.setQuantity(qty);
                return cartItem;
            }
        }
        System.out.println("OUaaai ouai");
        return newCartItem;
    }

    @Override
    public void removeCartItemFromCart(CartItem cartItem, AccountInfo customer) {
        //TODO: Clean this
        Optional<Cart> cart = findCartByCustomer(customer);
        if (cart.isPresent()) {
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
