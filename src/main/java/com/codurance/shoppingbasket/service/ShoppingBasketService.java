package com.codurance.shoppingbasket.service;

import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.model.ShoppingBasketFactory;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import shoppingbasket.model.ProductOrder;
import shoppingbasket.repositories.ShoppingBasketRepository;

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
        String userKey = Integer.toString(userId);

        if (!shoppingBaskets.containsKey(userKey)) {
            ShoppingBasket newShoppingBasket = shoppingBasketFactory.create(userId);

            shoppingBaskets.put(userKey, newShoppingBasket);
        }

        basketFor(userId).add(new ProductOrder(productRepository.find(itemId), quantity));
        shoppingBasketRepository.save(basketFor(userId));
    }

    public ShoppingBasket basketFor(int userId) {
        return shoppingBaskets.getOrDefault(Integer.toString(userId), null);
    }
}
