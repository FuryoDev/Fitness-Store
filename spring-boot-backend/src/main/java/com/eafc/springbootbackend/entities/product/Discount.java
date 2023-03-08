package com.eafc.springbootbackend.entities.product;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    private String discountName;
    @NotNull
    private int percentage;
    private boolean isActive;

    @OneToMany(mappedBy = "discount")
    private Collection<ProductInfo> discountedProducts;
}
