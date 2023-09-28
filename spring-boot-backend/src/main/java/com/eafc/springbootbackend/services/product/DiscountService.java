package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Discount;
import com.eafc.springbootbackend.entities.product.ProductInfo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DiscountService {

    Optional<Discount> findDiscountById(Long discountId);
    Collection<Discount> findActiveDiscounts();

    Collection<Discount> findAllDiscount();

    void saveDiscount(Discount discount);

    void deleteDiscount(Long discountId);

    void assignProductsToDiscount(List<ProductInfo> products, Long discountId);
}
