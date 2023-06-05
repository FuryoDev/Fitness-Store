package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.entities.customer.ShippingAddress;
import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.entities.shopping.CartItem;
import com.eafc.springbootbackend.entities.shopping.OrderInfo;
import com.eafc.springbootbackend.entities.shopping.OrderItem;
import com.eafc.springbootbackend.services.customer.CustomerService;
import com.eafc.springbootbackend.services.product.CartItemService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckOutService {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    private final CustomerService customerService;

    private final CartService cartService;

    private final CartItemService cartItemService;


    public CheckoutServiceImpl(OrderService orderService, OrderItemService orderItemService, CustomerService customerService, CartService cartService, CartItemService cartItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @Override
    public void processCheckout(Collection<CartItem> cartItems, OrderInfo orderInfo, String username, ShippingAddress shippingAddress) {
        //We create the order and retrieve it at the same time
        Cart cart = retrieveLoggedInUserCart(username);
        System.out.println(shippingAddress.getCity());

        OrderInfo newOrder = saveOrder(cartItems, orderInfo);
        mapCartItemsToOrderItems(cartItems, newOrder);
        AccountInfo customer = customerService.findByUsername(username);
        newOrder.setCustomer(customer);
        orderService.saveOrder(newOrder);
        emptyCart(cart);

    }

    private OrderInfo saveOrder(Collection<CartItem> cartItems, OrderInfo orderInfo) {
        OrderInfo newOrder = createOrder(orderInfo);
        newOrder.setTotal(calculateTotal(cartItems));
        newOrder = orderService.saveOrder(newOrder);
        return newOrder;
    }

    private Cart retrieveLoggedInUserCart(String username) {
        AccountInfo accountInfo = customerService.findByUsername(username);
        return cartService.findCartByCustomer(accountInfo).get();
    }

    @Override
    public void mapCartItemsToOrderItems(Collection<CartItem> cartItems, OrderInfo orderInfo) {
        Collection<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(cartItem.getProductInfo());
            orderItem.setPurchasedPrice(cartItem.getProductInfo().getPrice());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItem.setRelatedStockAndSize(cartItem.getRelatedSizeAndStock());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(orderInfo);
            orderItems.add(orderItem);
            orderItemService.saveOrderItem(orderItem);
        }
    }

    public OrderInfo createOrder(OrderInfo orderInfo) {
        orderInfo.setPurchaseDate(Calendar.getInstance());
        orderInfo.setPending(true);
        return orderInfo;
    }

    @Override
    public double calculateTotal(Collection<CartItem> cartItems) {
        double total = 0.0;
        for (CartItem cartItem : cartItems) {
            double itemPrice = cartItem.getProductInfo().getPrice();
            int itemQuantity = cartItem.getQuantity();
            total += itemPrice * itemQuantity;
        }
        return total;
    }


    @Override
    public void emptyCart(Cart cart) {
        Collection<CartItem> cartItems = cartItemService.findCartItemsByCart(cart.getCartId());
        for (CartItem cartItem : cartItems) {
            cartItemService.deleteCartItem(cartItem.getCartItemId());
        }
    }


    @Override
    public void updateStocks() {

    }
}
