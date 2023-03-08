package com.eafc.springbootbackend.entities.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
public class CustomerInfo implements Serializable, UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int customerId;

    private String name;

    private String lastName;

    private String username;

    @Email
    private String email;

    private Calendar birthday;

    @OneToOne
    private ShippingAddress shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = )
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set = new HashSet<>();
        this.userRoles.forEach(userRole ->  {
            set.add(new Authority(userRole.getRole().getRoleName)))
        });
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
