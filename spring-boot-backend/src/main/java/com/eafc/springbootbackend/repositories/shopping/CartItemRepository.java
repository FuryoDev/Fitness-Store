package com.eafc.springbootbackend.repositories.shopping;

import com.eafc.springbootbackend.entities.shopping.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
