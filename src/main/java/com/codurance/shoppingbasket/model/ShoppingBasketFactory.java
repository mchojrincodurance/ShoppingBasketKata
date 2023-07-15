package com.codurance.shoppingbasket.model;

public class ShoppingBasketFactory {
    public ShoppingBasket create(int userId) {

        return new ShoppingBasket(userId);
    }
}
