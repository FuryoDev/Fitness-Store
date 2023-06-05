package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.Stock;
import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.entities.shopping.CartItem;
import com.eafc.springbootbackend.repositories.shopping.CartItemRepository;
import com.eafc.springbootbackend.repositories.shopping.CartRepository;
import com.eafc.springbootbackend.services.customer.CustomerService;
import com.eafc.springbootbackend.services.product.ProductService;
import com.eafc.springbootbackend.services.product.StockService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final ProductService productService;
    private final CustomerService customerService;

    private final StockService stockService;

    //TODO: Replace the repos with services
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, CustomerService customerService, ProductService productService, StockService stockService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.customerService = customerService;
        this.productService = productService;
        this.stockService = stockService;
    }

    @Override
    public Optional<Cart> findCartById(Long cartId) {
        return cartRepository.findById(cartId);
    }

    @Override
    public Optional<Cart> findCartByCustomer(AccountInfo customer) {
        Cart cart = new Cart();
        return cartRepository.findById(customer.getCart().getCartId());
    }

    //TODO: Exctract all the methods here into smaller ones
    @Override
    public void addCartItemToCart(CartItem cartItem, String customerUsername) {
        AccountInfo customer = customerService.findByUsername(customerUsername);
        Stock stock = stockService.findStockById(cartItem.getRelatedSizeAndStock().getStockId()).get();
        int remainingAmount = stock.getItemsInStock() - cartItem.getQuantity();
        stock.setItemsInStock(remainingAmount);
        stockService.saveStock(stock);
        Cart cart = findCartByCustomer(customer).get();
        //TODO: Get the product from the DB and re-associate it to the cartItem
        Collection<CartItem> cartItems = cartItemRepository.findCartItemsByCart(cart);
        CartItem ci = findItemInCart(cartItems, cartItem);
        //TODO: Something different needs to be done about this null check
        ProductInfo product = productService.findProductById(cartItem.getProductInfo().getProductId());
        ci.setProductInfo(product);
        System.out.println("Petit check vite fait");
        //Calculate the cartitem total
        double total = ci.getProductInfo().getPrice() * ci.getQuantity();
        ci.setTotalPrice(total);
        cartRepository.save(cart);
        ci.setCart(cart);
        cartItemRepository.save(ci);
    }

    @Override
    public CartItem findItemInCart(Collection<CartItem> itemsInCart, CartItem newCartItem) {
        for (CartItem cartItem : itemsInCart) {
            if (cartItem.getProductInfo().equals(newCartItem.getProductInfo()) && cartItem.getRelatedSizeAndStock().getStockId().equals(newCartItem.getRelatedSizeAndStock().getStockId())) {
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
//            cart.get().getCartItems().remove(cartItem);
            cartRepository.save(cart.get());
        }
    }

    @Override
    public void emptyCart(Cart cart) {
        //TODO: Implement this;
//        cart.getCartItems().removeAll(cart.getCartItems());
        cartRepository.save(cart);
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }
}
