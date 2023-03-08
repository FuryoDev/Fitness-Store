package com.eafc.springbootbackend.repositories.shopping;

import com.eafc.springbootbackend.entities.customer.CustomerInfo;
import com.eafc.springbootbackend.entities.shopping.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderRepository extends JpaRepository<OrderInfo, Integer> {

    Collection<OrderInfo> findOrdersByCustomer(CustomerInfo customer);
}
