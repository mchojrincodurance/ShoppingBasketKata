package com.codurance.shoppingbasket.model;

import java.util.ArrayList;

public class ShoppingBasket {
    private final int ownerId;
    private final ArrayList products = new ArrayList();

    public ShoppingBasket(int ownerId)
    {
        this.ownerId = ownerId;
    }

    public int ownerId() {
        return ownerId;
    }

    public ArrayList products() {
        return products;
    }
}
