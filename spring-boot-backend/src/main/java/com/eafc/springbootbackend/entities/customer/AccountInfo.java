package com.eafc.springbootbackend.entities.customer;

import com.eafc.springbootbackend.entities.shopping.Cart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
public class AccountInfo implements Serializable, UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long customerId;

    private String name;

    private String lastName;

    private String username;

    @Email
    private String email;

    private Calendar birthday;

    @OneToOne
    private Cart cart;

    @OneToOne
    private ShippingAddress shippingAddress;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
