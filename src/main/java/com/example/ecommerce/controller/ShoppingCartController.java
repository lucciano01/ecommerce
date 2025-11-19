package com.example.ecommerce.controller;

import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.model.ShoppingCartItem;
import com.example.ecommerce.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinhos")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService){
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> saveShoppingCart(){
        ShoppingCart c = shoppingCartService.createShoppingCart();
        return ResponseEntity.ok(c);
    }

    @PostMapping("/{id}/itens")
    public ResponseEntity<ShoppingCartItem> adicionarItem(@PathVariable Long id,
                                                          @RequestParam Long produtoId,
                                                          @RequestParam int quantidade){
        ShoppingCartItem item = shoppingCartService.addItem(produtoId, quantidade);
        // find cart and add item (simple demo: not checking cart ownership)
        ShoppingCart c = shoppingCartService.findShoppingCart(id).orElse(shoppingCartService.createShoppingCart());
        c.addItem(item.getProduto(), item.getQuantidade());
        shoppingCartService.saveShoppingCart(c);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> buscar(@PathVariable Long id){
        return shoppingCartService.findShoppingCart(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
