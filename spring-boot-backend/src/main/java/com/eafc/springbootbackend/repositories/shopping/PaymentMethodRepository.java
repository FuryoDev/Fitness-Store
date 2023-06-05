package com.eafc.springbootbackend.repositories.shopping;

import com.eafc.springbootbackend.entities.shopping.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    boolean existsByName(String paymentMethodName);
}
