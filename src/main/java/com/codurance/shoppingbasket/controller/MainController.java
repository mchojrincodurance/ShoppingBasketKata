package com.codurance.shoppingbasket.controller;

import com.codurance.shoppingbasket.infrastructure.MyConsole;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import com.codurance.shoppingbasket.service.ShoppingBasketService;

public class MainController {
    private final ShoppingBasketService shoppingBasketService;
    private ProductRepository productRepository;

    public MainController(MyConsole console, ShoppingBasketService shoppingBasketService, ProductRepository productRepository) {
        this.shoppingBasketService = shoppingBasketService;
        this.productRepository = productRepository;
    }

    public void checkBasketContent(int userId) {
    }

    public void addItem(int userId, String productName, int quantity) {
        shoppingBasketService.addItem(userId, findIdFor(productName), quantity);
    }

    private int findIdFor(String productName) {

        return productRepository.find(productName).id();
    }
}
