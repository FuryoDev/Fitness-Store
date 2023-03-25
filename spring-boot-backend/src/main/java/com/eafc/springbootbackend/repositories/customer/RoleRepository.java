package com.eafc.springbootbackend.repositories.customer;

import com.eafc.springbootbackend.entities.customer.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
