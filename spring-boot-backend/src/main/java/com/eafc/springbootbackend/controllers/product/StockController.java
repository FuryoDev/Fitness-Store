package com.eafc.springbootbackend.controllers.product;

import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.Stock;
import com.eafc.springbootbackend.services.product.ProductService;
import com.eafc.springbootbackend.services.product.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

//    @GetMapping("getStockById")
    public Stock getStockById(@RequestParam("id") Long stockId) {
        return stockService.findStockById(stockId).get();
    }

//    @GetMapping("getAllStocks")
    public Collection<Stock> getAllStocks() {
        return stockService.findAllStocks();
    }

    @GetMapping("stocksByProduct")
    public Collection<Stock> getStocksByProduct(@RequestParam("productId") Long productId) {
        return stockService.findStocksByProduct(productId);
    }
}
