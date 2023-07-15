package com.codurance.shoppingbasket.service;

import com.codurance.shoppingbasket.model.ShoppingBasket;

import java.util.HashMap;

public class ShoppingBasketService {
    private HashMap<String, ShoppingBasket> shoppingBaskets = new HashMap<String, ShoppingBasket>();

    public void addItem(int userId, int itemId, int quantity) {
        if (!shoppingBaskets.containsKey(Integer.toString(userId))) {
            shoppingBaskets.put(Integer.toString(userId), new ShoppingBasket(userId));
        }
    }

    public ShoppingBasket basketFor(int userId) {
        return shoppingBaskets.containsKey(Integer.toString(userId)) ? shoppingBaskets.get(Integer.toString(userId)) : null;
    }
}
