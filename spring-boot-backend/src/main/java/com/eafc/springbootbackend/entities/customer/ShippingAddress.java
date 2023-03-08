package com.eafc.springbootbackend.entities.customer;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ShippingAddress {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int shippingAddressId;

    private String address;

    private String zipCode;

    private String city;

    @OneToOne
    private CustomerInfo customer;
}
