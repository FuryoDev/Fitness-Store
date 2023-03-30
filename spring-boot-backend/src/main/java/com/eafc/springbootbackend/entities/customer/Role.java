package com.eafc.springbootbackend.entities.customer;

import com.eafc.springbootbackend.utils.ERole;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
