package com.eafc.springbootbackend.services.customer;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.entities.shopping.Cart;

public interface  CustomerService {

    AccountInfo createCustomer(AccountInfo customer);

    AccountInfo findByUsername(String username);

}
