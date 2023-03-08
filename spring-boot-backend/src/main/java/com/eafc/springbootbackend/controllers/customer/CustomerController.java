package com.eafc.springbootbackend.controllers.customer;

import com.eafc.springbootbackend.entities.customer.CustomerInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class CustomerController {

    @GetMapping("/login-page")
    public String login() {
        return "sss";
    }

    @PostMapping("/register-user")
    public CustomerInfo createCustomerAccount(@RequestBody CustomerInfo newCustomer){
        Set<UserRole> roles = new HashSet<>();
        Role role = new Role():
    }

}
