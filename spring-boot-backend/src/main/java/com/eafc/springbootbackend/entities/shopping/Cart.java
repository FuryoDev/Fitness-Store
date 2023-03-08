package com.eafc.springbootbackend.entities.shopping;

import com.eafc.springbootbackend.entities.customer.CustomerInfo;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Collection<CartItem> cartItems;

    @OneToOne
    @JoinColumn(name = "customer")
    private CustomerInfo customerInfo;

    @NotNull
    private double totalPrice;
}
