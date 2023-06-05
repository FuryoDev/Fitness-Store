package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.entities.shopping.OrderInfo;
import com.eafc.springbootbackend.entities.shopping.OrderItem;

import java.util.Collection;
import java.util.Optional;

public interface OrderService {

    Optional<OrderInfo> findOrderById(Long orderId);

    Collection<OrderInfo> findOrdersByCustomer(String username);

    OrderInfo saveOrder(OrderInfo orderInfo);

    Collection<OrderItem> retrieveOrdersItemByOrder(Long orderId);

}
