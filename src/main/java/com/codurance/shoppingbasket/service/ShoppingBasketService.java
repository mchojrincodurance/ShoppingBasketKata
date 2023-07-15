package com.codurance.shoppingbasket.service;

import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.model.ShoppingBasketFactory;
import shoppingbasket.repositories.ShoppingBasketRepository;

import java.util.HashMap;

public class ShoppingBasketService {
    private final HashMap<String, ShoppingBasket> shoppingBaskets = new HashMap<>();
    private final ShoppingBasketFactory shoppingBasketFactory;
    private final ShoppingBasketRepository shoppingBasketRepository;

    public ShoppingBasketService(ShoppingBasketFactory shoppingBasketFactory, ShoppingBasketRepository shoppingBasketRepository)
    {
        this.shoppingBasketFactory = shoppingBasketFactory;
        this.shoppingBasketRepository = shoppingBasketRepository;
    }

    public void addItem(int userId, int itemId, int quantity) {
        String userKey = Integer.toString(userId);

        if (!shoppingBaskets.containsKey(userKey)) {
            ShoppingBasket newShoppingBasket = shoppingBasketFactory.create(userId);

            shoppingBaskets.put(userKey, newShoppingBasket);
        }

//        basketFor(userId).add(new Product(itemId, "", 10));
    }

    public ShoppingBasket basketFor(int userId) {
        return shoppingBaskets.getOrDefault(Integer.toString(userId), null);
    }
}
