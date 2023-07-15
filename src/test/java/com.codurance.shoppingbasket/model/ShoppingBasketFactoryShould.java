package com.codurance.shoppingbasket.model;

import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.model.ShoppingBasketFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingBasketFactoryShould {
    private ShoppingBasketFactory shoppingBasketFactory = new ShoppingBasketFactory();

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    public void create_baskets_for_users(int userId) {
        ShoppingBasket createdBasket = shoppingBasketFactory.create(userId);
        assertEquals(userId, createdBasket.ownerId());
    }
}
