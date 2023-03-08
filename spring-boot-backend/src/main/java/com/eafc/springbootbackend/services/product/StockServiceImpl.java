package com.eafc.springbootbackend.services.product;

import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.Stock;
import com.eafc.springbootbackend.repositories.product.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Optional<Stock> findStockById(int stockId) {
        return stockRepository.findById(stockId);
    }

    @Override
    public Collection<Stock> findStocksByProduct(ProductInfo productInfo) {
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
    public void deleteStock(int id) {

    }
}
