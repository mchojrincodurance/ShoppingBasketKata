package com.codurance.shoppingbasket.model;

import java.util.ArrayList;

public class ShoppingBasket {
    private final int ownerId;

    public ShoppingBasket(int ownerId)
    {
        this.ownerId = ownerId;
    }

    public int ownerId() {
        return ownerId;
    }

    public ArrayList products() {
        throw new UnsupportedOperationException("com.codurance.shoppingbasket.model.ShoppingBasket::ownerId not implemented yet");
    }
}
