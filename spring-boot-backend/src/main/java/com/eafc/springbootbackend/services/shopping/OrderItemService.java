package com.eafc.springbootbackend.services.shopping;

import com.eafc.springbootbackend.entities.shopping.OrderInfo;
import com.eafc.springbootbackend.entities.shopping.OrderItem;
import org.hibernate.criterion.Order;

import java.util.Collection;

public interface OrderItemService {

    void saveOrderItem(OrderItem orderItem);

    Collection<OrderItem> getOrderItemsByOrder(OrderInfo order);
}
