package com.eafc.springbootbackend.services.customer;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.repositories.customer.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AccountInfo> customer = customerRepository.findByUsername(username);
        if(customer.isPresent()) {
            throw new IllegalStateException("SOMETHING VERY WORONG WHEN FINDING USER");
        }
        return customer.get();
    }
}
