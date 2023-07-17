package com.codurance.shoppingbasket.model;

public class ProductOrder {
    private final Product product;
    private final int quantity;

    public ProductOrder(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product product()
    {
        return product;
    }

    public int quantity()
    {
        return quantity;
    }

    public float subtotal() {
        return product().price() * quantity;
    }
}
