package com.eafc.springbootbackend.entities.shopping;

import com.eafc.springbootbackend.entities.product.ProductInfo;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "product")
    private ProductInfo product;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "orderInfo")
    private OrderInfo order;

    @NotNull
    private double purchasedPrice;

    @NotNull
    private String size;

    @NotNull
    private int quantity;
}
