package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ProductServiceTest {

    @Autowired
    private ProductService productService;



    @Test
    @DisplayName(value = "Deveria salvar um produto na base de dados")
    void shouldSaveProduct(){
        var product = Product.builder()
                .price(50.0)
                .description("Mouse")
                .stock(2)
                .build();



        var result = productService.save(product);

        assertEquals(product.getId(), result.getId());
    }

    @Test
    @DisplayName(value = "Deveria lançar excecao quando a descricao do produto estiver vazia")
    void shouldThrowExceptionWhenInvalidDescriptionProduct(){
        var invalidProduct = Product.builder()
                .price(100.0)
                .stock(2)
                .build();

       assertThrows(DataIntegrityViolationException.class, () ->
               productService.save(invalidProduct)
               );


    }


}