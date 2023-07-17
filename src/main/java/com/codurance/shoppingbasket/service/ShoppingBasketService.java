package com.codurance.shoppingbasket.service;

import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.model.ShoppingBasketFactory;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import com.codurance.shoppingbasket.repositories.ShoppingBasketRepository;

import java.util.HashMap;

public class ShoppingBasketService {
    private final HashMap<String, ShoppingBasket> shoppingBaskets = new HashMap<>();
    private final ShoppingBasketFactory shoppingBasketFactory;
    private final ShoppingBasketRepository shoppingBasketRepository;
    private final ProductRepository productRepository;

    public ShoppingBasketService(ShoppingBasketFactory shoppingBasketFactory, ShoppingBasketRepository shoppingBasketRepository, ProductRepository productRepository)
    {
        this.shoppingBasketFactory = shoppingBasketFactory;
        this.shoppingBasketRepository = shoppingBasketRepository;
        this.productRepository = productRepository;
    }

    public void addItem(int userId, int itemId, int quantity) {
        createBasketFor(userId);
        ShoppingBasket shoppingBasket = basketFor(userId);
        shoppingBasket.add(productRepository.find(itemId), quantity);
        shoppingBasketRepository.save(shoppingBasket);
    }

    private void createBasketFor(int userId) {
        String userKey = Integer.toString(userId);
        if (!shoppingBaskets.containsKey(userKey)) {
            shoppingBaskets.put(userKey, shoppingBasketFactory.create(userId));
        }
    }

    public ShoppingBasket basketFor(int userId) {
        return shoppingBaskets.getOrDefault(Integer.toString(userId), null);
    }
}
