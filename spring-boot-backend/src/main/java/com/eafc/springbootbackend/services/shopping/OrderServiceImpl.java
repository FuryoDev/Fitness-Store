package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.entities.shopping.OrderInfo;
import com.eafc.springbootbackend.entities.shopping.OrderItem;
import com.eafc.springbootbackend.repositories.shopping.OrderRepository;
import com.eafc.springbootbackend.services.customer.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemService orderItemService;

    private final CustomerService customerService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
    }

    @Override
    public Optional<OrderInfo> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Collection<OrderInfo> findOrdersByCustomer(String username) {
        AccountInfo customer = customerService.findByUsername(username);
        return orderRepository.findOrdersByCustomer(customer);
    }

    @Override
    public OrderInfo saveOrder(OrderInfo orderInfo) {
        return orderRepository.save(orderInfo);
    }

    @Override
    public Collection<OrderItem> retrieveOrdersItemByOrder(Long orderId) {
        OrderInfo orderInfo = this.orderRepository.findById(orderId).get();
        return this.orderItemService.getOrderItemsByOrder(orderInfo);
    }
}
