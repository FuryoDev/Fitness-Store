package com.eafc.springbootbackend.entities.shopping;

import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.Stock;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product")
    private ProductInfo productInfo;

    @NotNull
    private int quantity;

    @NotNull
    private double totalPrice;


    @OneToOne
    private Stock relatedSizeAndStock;
}
