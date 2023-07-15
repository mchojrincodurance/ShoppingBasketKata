package com.codurance.shoppingbasket.model;

import shoppingbasket.model.ProductOrder;

import java.util.ArrayList;

public class ShoppingBasket {
    private final int ownerId;
    private final ArrayList<ProductOrder> productOrders = new ArrayList();

    public ShoppingBasket(int ownerId)
    {
        this.ownerId = ownerId;
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
}
