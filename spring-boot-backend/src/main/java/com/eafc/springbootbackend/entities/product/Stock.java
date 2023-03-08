package com.eafc.springbootbackend.entities.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stockId;

    @NotNull
    @NotBlank
    private String size;

    @NotNull
    private int itemsInStock;

    @NotNull
    private int maxItems;

    @JsonBackReference(value = "stock-prod")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "product")
    private ProductInfo product;
}
