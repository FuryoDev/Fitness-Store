package com.eafc.springbootbackend.repositories.customer;

import com.eafc.springbootbackend.entities.customer.Role;
import com.eafc.springbootbackend.utils.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole eRole);
}
