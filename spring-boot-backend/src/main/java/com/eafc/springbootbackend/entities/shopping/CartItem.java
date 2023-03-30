package com.eafc.springbootbackend.entities.shopping;

import com.eafc.springbootbackend.entities.product.ProductInfo;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "cart")
    private Cart cart;

    //TODO: Check if it's the right cascade we need
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product")
    private ProductInfo productInfo;

    @NotNull
    private int quantity;

    @NotNull
    private double totalPrice;

    @NotNull
    private String size;
}
