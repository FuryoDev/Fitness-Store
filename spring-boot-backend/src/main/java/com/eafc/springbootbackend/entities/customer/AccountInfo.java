package com.eafc.springbootbackend.entities.customer;

import com.eafc.springbootbackend.entities.shopping.Cart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
public class AccountInfo implements Serializable, UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long customerId;

    @NotNull(message = "Name was sent null")
    @NotBlank(message = "Name shouldn't be blank")
    @Size(max = 40, message = "Size for the name should be between 5 and 40 characters")
    private String name;

    @OneToOne
    private Cart cart;

    @Size(max = 40, message = "Size for the name should be between 5 and 40 characters")
    private String lastName;

    @NotNull(message = "Name was sent null")
    @NotBlank(message = "Name shouldn't be blank")
    @Size(max = 40, message = "Size for the name should be between 5 and 40 characters")
    private String username;

    @NotNull(message = "Name was sent null")
    @NotBlank(message = "Name shouldn't be blank")
    private String password;

    @Email
    @NotNull(message = "Name was sent null")
    @NotBlank(message = "Name shouldn't be blank")
    private String email;

    private Calendar birthday;

    @OneToOne
    private ShippingAddress shippingAddress;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
