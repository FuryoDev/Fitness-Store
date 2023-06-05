package com.eafc.springbootbackend.entities.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @NotNull
    @NotBlank
    private String size;

    @NotNull
    @Min(0)
    private int itemsInStock;

    @NotNull
    @Min(0)
    private int maxItems;

    @JsonBackReference(value = "stock-prod")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductInfo product;
}
