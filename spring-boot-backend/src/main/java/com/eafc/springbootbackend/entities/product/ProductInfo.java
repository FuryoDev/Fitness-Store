package com.eafc.springbootbackend.entities.product;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

@Entity
@Data
public class ProductInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 40)
    private String name;

    private double price;

    private String imageURL;

    private String description;

    private Calendar creationDate;

    @ManyToOne
    @JoinColumn(name = "subCategory")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Collection<Stock> stocks;

    @ManyToOne
    @JoinColumn(name = "discount")
    private Discount discount;

}
