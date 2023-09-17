package com.eafc.springbootbackend.services.customer;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.entities.shopping.Cart;
import com.eafc.springbootbackend.repositories.customer.AccountInfoRepository;
import com.eafc.springbootbackend.repositories.customer.RoleRepository;
import com.eafc.springbootbackend.services.shopping.CartService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final AccountInfoRepository accountInfoRepository;

    private final RoleRepository roleRepository;


    public CustomerServiceImpl(AccountInfoRepository accountInfoRepository, RoleRepository roleRepository) {
        this.accountInfoRepository = accountInfoRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AccountInfo createCustomer(AccountInfo customer) {
        AccountInfo accountInfo = this.accountInfoRepository.findByUsername(customer.getUsername()).get();
        System.out.println("Customer already exists");
        try {
            throw new Exception("Username already exists");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public AccountInfo findByUsername(String username) {
        return accountInfoRepository.findByUsername(username).get();
    }


}
