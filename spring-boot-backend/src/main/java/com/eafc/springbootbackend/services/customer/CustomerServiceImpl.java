package com.eafc.springbootbackend.services.customer;

import com.eafc.springbootbackend.entities.customer.CustomerInfo;
import com.eafc.springbootbackend.repositories.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerInfo> customer = customerRepository.findByUsername(username);
        if(customer.isPresent()) {
            throw new IllegalStateException("SOMETHING VERY WORONG");
        }
        return customer.get();
    }
}
