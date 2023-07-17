package com.codurance.shoppingbasket.service;

import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.model.ShoppingBasketFactory;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import com.codurance.shoppingbasket.model.ProductOrder;
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
        shoppingBasket.add(createProductOrder(itemId, quantity));
        shoppingBasketRepository.save(shoppingBasket);
    }

    private ProductOrder createProductOrder(int itemId, int quantity) {
        return new ProductOrder(productRepository.find(itemId), quantity);
    }

    private void createBasketFor(int userId) {
        String userKey = Integer.toString(userId);
        if (!shoppingBaskets.containsKey(userKey)) {
            ShoppingBasket newShoppingBasket = shoppingBasketFactory.create(userId);

            shoppingBaskets.put(userKey, newShoppingBasket);
        }
    }

    public ShoppingBasket basketFor(int userId) {
        return shoppingBaskets.getOrDefault(Integer.toString(userId), null);
    }
}
