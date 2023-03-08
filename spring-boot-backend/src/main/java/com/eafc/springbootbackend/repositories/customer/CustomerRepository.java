package com.eafc.springbootbackend.repositories.customer;

import com.eafc.springbootbackend.entities.customer.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerInfo, Integer> {
    Optional<CustomerInfo> findByUsername(String username);
}
