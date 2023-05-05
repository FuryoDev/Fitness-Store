package com.eafc.springbootbackend.controllers.customer;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.services.customer.CustomerServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login-page")
    public String login() {
        return "sss";
    }

    @PostMapping("/register-user")
    public AccountInfo createCustomerAccount(@RequestBody AccountInfo newCustomer){
        //TODO: Add the Authority logic here depending on the nature of the new account
        return customerService.createCustomer(newCustomer);
    }
}
