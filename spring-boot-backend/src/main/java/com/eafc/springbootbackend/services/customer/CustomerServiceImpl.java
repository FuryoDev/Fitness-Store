package com.eafc.springbootbackend.services.customer;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.repositories.customer.CustomerRepository;
import com.eafc.springbootbackend.repositories.customer.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final RoleRepository roleRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, RoleRepository roleRepository) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AccountInfo createCustomer(AccountInfo customer) {
        AccountInfo accountInfo = this.customerRepository.findByUsername(customer.getUsername()).get();
        System.out.println("Customer already exists");
        try {
            throw new Exception("User already exists my man");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
