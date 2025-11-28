package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductMockServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void shouldSaveProduct(){
        var product = Product.builder()
                .price(50.0)
                .description("Mouse")
                .stock(2)
                .build();

        when(productRepository.save(product)).thenReturn(product);

        var result = productService.save(product);
        assertNotNull(result);
        assertEquals(product.getId(), result.getId());
    }

    @Test
     void shouldFoundProductById(){

        var product = Product.builder()
                .price(50.0)
                .description("Mouse")
                .stock(2)
                .build();

        var productId = 1L;

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        var productFound = productService.findById(productId);

        assertNotNull(productFound);
        assertEquals(productFound.getId(), product.getId());

     }

     @Test
     void shouldGetAllProducts(){

         var product1 = Product.builder()
                 .price(50.0)
                 .description("Mouse")
                 .stock(2)
                 .build();

         var product2 = Product.builder()
                 .price(80.0)
                 .description("Teclado")
                 .stock(1)
                 .build();

        when(productRepository.findAll())
                .thenReturn(List.of(product1, product2));

        var products = productService.findAll();

        assertFalse(products.isEmpty());

     }
}
