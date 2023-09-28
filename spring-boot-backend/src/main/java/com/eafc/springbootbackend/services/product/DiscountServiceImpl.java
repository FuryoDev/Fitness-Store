package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.Discount;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.repositories.product.DiscountRepository;
import com.eafc.springbootbackend.repositories.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository, ProductRepository productRepository) {
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
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
        discountRepository.save(discount);
    }

    @Override
    public void deleteDiscount(Long discountId) {
        discountRepository.deleteById(discountId);
    }

    @Override
    public void assignProductsToDiscount(List<ProductInfo> products, Long discountId) {
        Discount discount  = discountRepository.findById(discountId).get();
        for (ProductInfo product : products) {
            product.setDiscount(discount);
        }
        productRepository.saveAll(products);
    }
}
