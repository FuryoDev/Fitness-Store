package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Discount;

import java.util.Collection;
import java.util.Optional;

public interface DiscountService {

    Optional<Discount> findDiscountById(int discountId);
    Collection<Discount> findActiveDiscounts();

    Collection<Discount> findAllDiscount();

    void saveDiscount(Discount discount);

    void deleteDiscount(int discountId);
}
