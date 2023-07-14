package com.codurance.shoppingbasket.controller;

import com.codurance.shoppingbasket.infrastructure.MyConsole;
import com.codurance.shoppingbasket.service.ShoppingBasketService;

public class MainController {
    private final ShoppingBasketService shoppingBasketService;

    public MainController(MyConsole console, ShoppingBasketService shoppingBasketService) {
        this.shoppingBasketService = shoppingBasketService;
    }

    public void checkBasketContent(int i) {
    }

    public void addItem(int userId, String productName, int quantity) {
        shoppingBasketService.addItem(1, 1, 2);
    }
}
