package com.codurance.shoppingbasket.service;

import com.codurance.shoppingbasket.model.ShoppingBasket;

import java.util.HashMap;

public class ShoppingBasketService {
    private final HashMap<String, ShoppingBasket> shoppingBaskets = new HashMap<>();

    public void addItem(int userId, int itemId, int quantity) {
        if (!shoppingBaskets.containsKey(Integer.toString(userId))) {
            shoppingBaskets.put(Integer.toString(userId), new ShoppingBasket(userId));
        }
    }

    public ShoppingBasket basketFor(int userId) {
        return shoppingBaskets.getOrDefault(Integer.toString(userId), null);
    }
}
