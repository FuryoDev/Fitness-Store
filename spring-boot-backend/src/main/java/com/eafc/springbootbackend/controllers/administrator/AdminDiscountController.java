package com.eafc.springbootbackend.controllers.administrator;

import com.eafc.springbootbackend.entities.product.Category;
import com.eafc.springbootbackend.entities.product.Discount;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.services.product.DiscountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminDiscountController {

    private final DiscountService discountService;

    public AdminDiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("getDiscountById")
    public Discount getDiscountById(@RequestParam("discId") Long discountId) {
        return discountService.findDiscountById(discountId).get();
    }

    @GetMapping("getAllDiscounts")
    public Collection<Discount> getAllDiscounts() {
        return discountService.findAllDiscount();
    }

    @PostMapping(value = "saveDiscount", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void saveDiscount(@RequestPart("discount") Discount discount) {
        discountService.saveDiscount(discount);
    }

    @PostMapping(value = "assignProductToDiscount")
    public void assignProductsToDiscount(@RequestParam("discountId") Long discountId, @RequestBody List<ProductInfo> products) {
        discountService.assignProductsToDiscount(products, discountId);
    }

    @DeleteMapping("deleteDiscount")
    public void deleteDiscount(@RequestParam("discId")Long discountId) {
        discountService.deleteDiscount(discountId);
    }
}
