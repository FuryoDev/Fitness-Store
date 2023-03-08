package com.eafc.springbootbackend.services.shopping;

public interface CheckOutService {

    //TODO: Check what params are needed when we check out
    void processCheckout();

    void emptyCart();

    void updateStocks();

}
