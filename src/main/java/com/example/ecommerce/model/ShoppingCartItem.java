package com.example.ecommerce.model;

public class ShoppingCartItem {
    private Product product;
    private int quantidade;

    public ShoppingCartItem() {}

    public ShoppingCartItem(Product product, int quantidade) {
        this.product = product;
        this.quantidade = quantidade;
    }

    public Product getProduto() { return product; }
    public void setProduto(Product product) { this.product = product; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public Double getSubtotal() {
        return product.getPrice() * quantidade;
    }
}
