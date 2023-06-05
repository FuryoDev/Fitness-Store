package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Discount;
import com.eafc.springbootbackend.repositories.product.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Optional<Discount> findDiscountById(Long discountId) {
        return discountRepository.findById(discountId);
    }

    @Override
    public Collection<Discount> findActiveDiscounts() {
        //TODO: Check how we gonna approach this
        return null;
    }

    @Override
    public Collection<Discount> findAllDiscount() {
        return discountRepository.findAll();
    }

    @Override
    public void saveDiscount(Discount discount) {
        if(discount.getDiscountId() == null) {

        }
        discountRepository.save(discount);
    }

    @Override
    public void deleteDiscount(Long discountId) {
        discountRepository.deleteById(discountId);
    }
}
