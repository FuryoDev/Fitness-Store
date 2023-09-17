package com.eafc.springbootbackend.entities.product;


import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotNull(message = "Name was sent null")
    @NotBlank(message = "Name shouldn't be blank")
    @Size(min = 5, max = 40, message = "Size for the name should be between 5 and 40 characters")
    private String name;

    @NotNull(message = "Name was sent null")
    @NotBlank(message = "Name shouldn't be blank")
    private String sizingType;

    private String imageURL;
}
