package com.codurance.shoppingbasket.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ShoppingBasket {
    private final int ownerId;
    private final LocalDate createdAt;
    private final ArrayList<ProductOrder> productOrders = new ArrayList<>();

    public ShoppingBasket(int ownerId, LocalDate createdAt)
    {
        this.ownerId = ownerId;
        this.createdAt = createdAt;
    }

    public int ownerId() {
        return ownerId;
    }

    public ArrayList<ProductOrder> productOrders() {
        return productOrders;
    }

    public void add(ProductOrder productOrder) {
        productOrders.add(productOrder);
    }

    public LocalDate createdAt()
    {
        return createdAt;
    }

    public float total() {
        float total = 0;

        for ( ProductOrder po : productOrders ) {
            total += po.subtotal();
        }

        return total;
    }
}
