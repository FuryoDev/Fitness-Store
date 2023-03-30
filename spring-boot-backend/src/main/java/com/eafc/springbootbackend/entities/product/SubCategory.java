package com.eafc.springbootbackend.entities.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategoryId;

    @NotNull
    @NotBlank
    private String name;

    @JsonBackReference(value = "stock-tttt")
    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private Collection<ProductInfo> productInfos;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
}
