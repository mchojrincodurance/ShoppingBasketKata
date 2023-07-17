package com.codurance.shoppingbasket.controller;

import com.codurance.shoppingbasket.presentation.ShoppingBasketConsoleRenderer;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import com.codurance.shoppingbasket.service.ShoppingBasketService;

public class MainController {
    private final ShoppingBasketService shoppingBasketService;
    private final ProductRepository productRepository;
    private final ShoppingBasketConsoleRenderer shoppingBasketRenderer;

    public MainController(ShoppingBasketConsoleRenderer renderer, ShoppingBasketService shoppingBasketService, ProductRepository productRepository) {
        this.shoppingBasketService = shoppingBasketService;
        this.productRepository = productRepository;
        this.shoppingBasketRenderer = renderer;
    }

    public void checkBasketContent(int userId) {
        shoppingBasketRenderer.render(shoppingBasketService.basketFor(userId));
    }

    public void addItem(int userId, String productName, int quantity) {
        shoppingBasketService.addItem(userId, findIdForProduct(productName), quantity);
    }

    private int findIdForProduct(String productName) {

        return productRepository.find(productName).id();
    }
}
