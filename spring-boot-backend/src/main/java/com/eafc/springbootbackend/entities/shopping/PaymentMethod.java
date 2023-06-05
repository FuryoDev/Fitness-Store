package com.eafc.springbootbackend.entities.shopping;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
public class PaymentMethod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentMethodId;

    @NotNull(message = "Name was sent null")
    @NotBlank(message = "Name shouldn't be blank")
    @Size(max = 40, message = "Size for the name should be between 5 and 40 characters")
    private String name;

    private String iconURL;

}
