package com.eafc.springbootbackend.repositories.customer;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<AccountInfo, Integer> {
    Optional<AccountInfo> findByUsername(String username);
}
