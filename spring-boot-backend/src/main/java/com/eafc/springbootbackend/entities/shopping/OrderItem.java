package com.eafc.springbootbackend.entities.shopping;

import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.Stock;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductInfo product;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "orderInfo")
    private OrderInfo order;

    @NotNull
    private double purchasedPrice;

    @NotNull
    private double totalPrice;

    @OneToOne
    private Stock relatedStockAndSize;

    @NotNull
    private int quantity;
}
