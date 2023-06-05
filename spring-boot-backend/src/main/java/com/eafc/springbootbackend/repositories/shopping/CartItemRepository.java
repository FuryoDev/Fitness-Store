package com.eafc.springbootbackend.repositories.shopping;

import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.entities.shopping.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Collection<CartItem> findCartItemsByCart(Cart cart);
}
