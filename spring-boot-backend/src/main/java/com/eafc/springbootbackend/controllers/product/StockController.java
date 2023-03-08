package com.eafc.springbootbackend.controllers.product;

import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.Stock;
import com.eafc.springbootbackend.services.product.ProductService;
import com.eafc.springbootbackend.services.product.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@CrossOrigin("http://localhost:4200")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

//    @GetMapping("getStockById")
    public Stock getStockById(@RequestParam("id") Integer stockId) {
        return stockService.findStockById(stockId).get();
    }

//    @GetMapping("getAllStocks")
    public Collection<Stock> getAllStocks() {
        return stockService.findAllStocks();
    }

//    @GetMapping("stocksByProduct")
    public Collection<Stock> getStocksByProduct(@RequestParam("id") Integer productId) {
        //return stockService.findStocksByProduct(productId);
        return null;
    }
}
