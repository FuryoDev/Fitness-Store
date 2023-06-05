package com.eafc.springbootbackend.entities.customer;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
public class ShippingAddress implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long shippingAddressId;

    @NotNull(message = "Street name was sent null")
    @NotBlank(message = "Street name shouldn't be blank")
    private String streetNameAndNumber;

    @NotNull(message = "Zipcode was sent null")
    @NotBlank(message = "Zipcode shouldn't be blank")
    private String zipCode;

    @NotNull(message = "City was sent null")
    @NotBlank(message = "SCity shouldn't be blank")
    private String city;

    @NotNull(message = "Country was sent null")
    @NotBlank(message = "Country shouldn't be blank")
    private String country;

    @OneToOne
    private AccountInfo customer;
}
