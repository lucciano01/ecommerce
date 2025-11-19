package com.example.ecommerce.service;

import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.model.ShoppingCartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ShoppingCarRepository;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ProductRepository productRepository;
    private final ShoppingCarRepository shoppingCarRepository;

    public ShoppingCartItem addItem(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }

        if (product.getStock() < quantity){
            throw new IllegalStateException("Estoque insuficiente");
        }

        return new ShoppingCartItem(product, quantity);
    }

    public ShoppingCart createShoppingCart() {
        var c = new ShoppingCart();
        return shoppingCarRepository.save(c);
    }

    public void saveShoppingCart(ShoppingCart shoppingCart) {
        shoppingCarRepository.save(shoppingCart);
    }

    public Optional<ShoppingCart> findShoppingCart(Long id) {
        return shoppingCarRepository.findById(id);
    }
}
