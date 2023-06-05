package com.eafc.springbootbackend.entities.shopping;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Collection;

@Entity
@Data
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @NotNull
    private Calendar purchaseDate;

    private boolean pending;

    @NotNull
    private double total;

//    @NotNull
    @ManyToOne
    @JoinColumn(name = "paymentMethod")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "customer")
    private AccountInfo customer;
}
