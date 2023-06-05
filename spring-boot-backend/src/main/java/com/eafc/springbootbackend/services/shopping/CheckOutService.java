package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.ShippingAddress;
import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.entities.shopping.CartItem;
import com.eafc.springbootbackend.entities.shopping.OrderInfo;
import com.eafc.springbootbackend.entities.shopping.OrderItem;

import java.util.Collection;
import java.util.List;

public interface CheckOutService {

    //TODO: Check what params are needed when we check out
    void processCheckout(Collection<CartItem> cartItems, OrderInfo orderInfo, String username, ShippingAddress shippingAddress);

    void emptyCart(Cart cart);

    void updateStocks();

    double calculateTotal(Collection<CartItem> cartItems);

    void mapCartItemsToOrderItems(Collection<CartItem> cartItems, OrderInfo orderInfo);

    OrderInfo createOrder(OrderInfo orderInfo);

}
