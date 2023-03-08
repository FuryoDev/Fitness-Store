package com.eafc.springbootbackend.entities.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Collection;

@Entity
@Data
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

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
