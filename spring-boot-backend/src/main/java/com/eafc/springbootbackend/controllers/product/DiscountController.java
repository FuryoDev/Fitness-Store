package com.eafc.springbootbackend.controllers.product;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.Discount;
import com.eafc.springbootbackend.services.product.CategoryService;
import com.eafc.springbootbackend.services.product.DiscountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("getDiscountById")
    public Discount getCategoryById(@RequestParam("id") Integer discountId) {
        return discountService.findDiscountById(discountId).get();
    }

    @GetMapping("getAllDiscounts")
    public Collection<Discount> getAllDiscounts() {
        return discountService.findAllDiscount();
    }
}
