package com.codurance.shoppingbasket.controller;

import com.codurance.shoppingbasket.infrastructure.MyConsole;
import com.codurance.shoppingbasket.service.ShoppingBasketService;

public class MainController {
    public MainController(MyConsole console) {
    }

    public MainController(ShoppingBasketService shoppingBasketService) {
    }

    public void checkBasketContent(int i) {
    }

    public void addItem(int userId, String productName, int quantity) {
        throw new UnsupportedOperationException("com.codurance.shoppingbasket.controller.MainController::addItem not implemented yet");
    }
}
