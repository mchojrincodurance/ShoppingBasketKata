package com.codurance.shoppingbasket.service;

import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.model.ShoppingBasketFactory;

import java.util.HashMap;

public class ShoppingBasketService {
    private final HashMap<String, ShoppingBasket> shoppingBaskets = new HashMap<>();
    private final ShoppingBasketFactory shoppingBasketFactory;

    public ShoppingBasketService(ShoppingBasketFactory shoppingBasketFactory)
    {
        this.shoppingBasketFactory = shoppingBasketFactory;
    }

    public void addItem(int userId, int itemId, int quantity) {
        if (!shoppingBaskets.containsKey(Integer.toString(userId))) {
            shoppingBaskets.put(Integer.toString(userId), shoppingBasketFactory.create(userId));
        }

//        basketFor(userId).add(new Product(itemId, "", 10));
    }

    public ShoppingBasket basketFor(int userId) {
        return shoppingBaskets.getOrDefault(Integer.toString(userId), null);
    }
}
