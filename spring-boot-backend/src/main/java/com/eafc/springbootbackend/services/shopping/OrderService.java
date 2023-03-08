package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.CustomerInfo;
import com.eafc.springbootbackend.entities.shopping.OrderInfo;

import java.util.Collection;
import java.util.Optional;

public interface OrderService {

    Optional<OrderInfo> findOrderById(int orderId);

    Collection<OrderInfo> findOrdersByCustomer(CustomerInfo customer);

    void saveOrder(OrderInfo orderInfo);

}
