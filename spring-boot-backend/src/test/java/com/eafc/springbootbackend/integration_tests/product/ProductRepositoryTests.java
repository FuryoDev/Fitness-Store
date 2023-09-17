package com.eafc.springbootbackend.integration_tests.product;

import com.eafc.springbootbackend.SpringBootBackendApplication;
import com.eafc.springbootbackend.configuration.H2JpaConfiguration;
import com.eafc.springbootbackend.entities.product.ProductInfo;
import com.eafc.springbootbackend.repositories.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {SpringBootBackendApplication.class, H2JpaConfiguration.class})
public class ProductRepositoryTests {

//    @Autowired
//    private ProductRepository productRepository;
//
//    @Test
//    public void saveProductTest() {
//        ProductInfo product = new ProductInfo();
//        product.setProductId(1L);
//        product.setName("aaaaa");
//        productRepository.save(product);
//
//        product = productRepository.findById(product.getProductId()).get();
//        assertNotNull(product);
//    }

}
