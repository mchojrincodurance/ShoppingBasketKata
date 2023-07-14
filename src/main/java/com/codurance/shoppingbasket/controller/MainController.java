package com.codurance.shoppingbasket.controller;

import com.codurance.shoppingbasket.infrastructure.ShoppingBasketRenderer;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import com.codurance.shoppingbasket.service.ShoppingBasketService;

public class MainController {
    private final ShoppingBasketService shoppingBasketService;
    private ProductRepository productRepository;
    private ShoppingBasketRenderer shoppingBasketRenderer;

    public MainController(ShoppingBasketRenderer renderer, ShoppingBasketService shoppingBasketService, ProductRepository productRepository) {
        this.shoppingBasketService = shoppingBasketService;
        this.productRepository = productRepository;
        this.shoppingBasketRenderer = renderer;
    }

    public void checkBasketContent(int userId) {
        shoppingBasketRenderer.render(shoppingBasketService.basketFor(userId));
    }

    public void addItem(int userId, String productName, int quantity) {
        shoppingBasketService.addItem(userId, findIdFor(productName), quantity);
    }

    private int findIdFor(String productName) {

        return productRepository.find(productName).id();
    }
}
