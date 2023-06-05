package com.eafc.springbootbackend.repositories.shopping;

import com.eafc.springbootbackend.entities.shopping.OrderInfo;
import com.eafc.springbootbackend.entities.shopping.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderItemRepository  extends JpaRepository<OrderItem, Long> {

    Collection<OrderItem> findOrderItemByOrder(OrderInfo orderInfo);
}
