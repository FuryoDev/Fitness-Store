package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.CustomerInfo;
import com.eafc.springbootbackend.entities.shopping.OrderInfo;
import com.eafc.springbootbackend.repositories.shopping.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<OrderInfo> findOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Collection<OrderInfo> findOrdersByCustomer(CustomerInfo customer) {
        return orderRepository.findOrdersByCustomer(customer);
    }

    @Override
    public void saveOrder(OrderInfo orderInfo) {
        orderRepository.save(orderInfo);
    }
}
