package com.eafc.springbootbackend.controllers.customer;

import com.eafc.springbootbackend.entities.shopping.OrderInfo;
import com.eafc.springbootbackend.entities.shopping.OrderItem;
import com.eafc.springbootbackend.services.shopping.OrderItemService;
import com.eafc.springbootbackend.services.shopping.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    public OrderController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("retrieveOrders")
    public Collection<OrderInfo> retrieveOrders(@RequestParam String username) {
        return  this.orderService.findOrdersByCustomer(username);
    }

    @GetMapping("getOrderById")
    public OrderInfo getOrderById(@RequestParam Long orderId) {
        return this.orderService.findOrderById(orderId).get();
    }

    @GetMapping("retrieveOrderItems")
    public Collection<OrderItem> retrieveOrderItems(@RequestParam Long orderId) {
        return this.orderService.retrieveOrdersItemByOrder(orderId);
    }
}
