package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.entities.shopping.OrderInfo;
import com.eafc.springbootbackend.repositories.shopping.OrderRepository;
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
    public Optional<OrderInfo> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Collection<OrderInfo> findOrdersByCustomer(AccountInfo customer) {
        return orderRepository.findOrdersByCustomer(customer);
    }

    @Override
    public void saveOrder(OrderInfo orderInfo) {
        orderRepository.save(orderInfo);
    }
}
