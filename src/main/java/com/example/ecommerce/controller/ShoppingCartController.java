package com.example.ecommerce.controller;

import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.model.ShoppingCartItem;
import com.example.ecommerce.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping
    public ResponseEntity<ShoppingCart> saveShoppingCart(){
        ShoppingCart c = shoppingCartService.createShoppingCart();
        return ResponseEntity.ok(c);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<ShoppingCartItem> addItem(@PathVariable Long id,
                                                    @RequestParam("productId") Long productId,
                                                    @RequestParam("quantity") int quantity){
        ShoppingCartItem item = shoppingCartService.addItem(productId, quantity);
        ShoppingCart c = shoppingCartService.findShoppingCart(id).orElse(shoppingCartService.createShoppingCart());
        c.addItem(item.getProduct(), item.getQuantity());
        shoppingCartService.saveShoppingCart(c);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> find(@PathVariable Long id){
        return shoppingCartService.findShoppingCart(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
