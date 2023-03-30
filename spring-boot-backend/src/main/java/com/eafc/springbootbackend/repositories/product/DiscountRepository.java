package com.eafc.springbootbackend.repositories.product;

import com.eafc.springbootbackend.entities.product.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
