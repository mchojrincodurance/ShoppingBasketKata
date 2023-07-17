package com.codurance.shoppingbasket.model;

import com.codurance.shoppingbasket.infrastructure.MyClock;

public class ShoppingBasketFactory {
    private MyClock clock;

    public ShoppingBasketFactory(MyClock clock)
    {
        this.clock = clock;
    }

    public ShoppingBasket create(int ownerId) {

        return new ShoppingBasket(ownerId, clock.getCurrentDate());
    }
}
