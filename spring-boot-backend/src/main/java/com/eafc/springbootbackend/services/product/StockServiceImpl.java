package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.Stock;
import com.eafc.springbootbackend.repositories.product.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    private final ProductService productService;

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository, ProductService productService) {
        this.stockRepository = stockRepository;
        this.productService = productService;
    }

    @Override
    public Optional<Stock> findStockById(Long stockId) {
        return stockRepository.findById(stockId);
    }

    @Override
    public Collection<Stock> findStocksByProduct(Long productId) {
        ProductInfo productInfo = productService.findProductById(productId);
        System.out.println("On passe ici pour le stock d'un prod");
        return stockRepository.findStocksByProduct(productInfo);
    }

    @Override
    public Collection<Stock> findAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public void refillStocks(Collection<Stock> stocksToRefill) {

    }

    @Override
    public void saveStock(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public void deleteStock(Long id) {

    }
}
