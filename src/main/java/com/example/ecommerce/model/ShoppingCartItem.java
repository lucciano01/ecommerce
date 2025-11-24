package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartItem {
    private Product product;
    private int quantity;


    public Double getSubtotal() {
        return product.getPrice() * quantity;
    }
}
