package com.eafc.springbootbackend.entities.product;

import com.eafc.springbootbackend.entities.shopping.CartItem;
import com.eafc.springbootbackend.services.shopping.CartServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
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

    @NotNull(message = "Name was sent null")
    @NotBlank(message = "Name shouldn't be blank")
    @Size(min = 5, max = 40, message = "Size for the name should be between 5 and 40 characters")
    private String name;

    @Min(0)
    private double price;

    private String imageURL;

    @Size(min = 5, max = 300, message = "Description should be between 5 and 300 characters")
    private String description;

    private String sex;

    private Calendar creationDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "subCategory", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SubCategory subCategory;

}
