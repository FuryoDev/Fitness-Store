package com.eafc.springbootbackend.repositories.product;

import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.entities.product.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Collection<Stock> findStocksByProduct(ProductInfo productInfo);
}
