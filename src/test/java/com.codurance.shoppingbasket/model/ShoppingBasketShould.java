package com.codurance.shoppingbasket.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingBasketShould {
    public static Stream<Arguments> shoppingBasketProvider() {
        ShoppingBasket secondShoppingBasket = new ShoppingBasket(1, LocalDate.now());
        ShoppingBasket firstShoppingBasket = new ShoppingBasket(1, LocalDate.now());

        Product product1 = new Product(1, "Product 1", 10);
        firstShoppingBasket.add(new ProductOrder(product1, 2));
        firstShoppingBasket.add(new ProductOrder(new Product(2, "Product 2", 2), 1));

        secondShoppingBasket.add(new ProductOrder(new Product(3, "Product 3", 15.6F), 1));
        secondShoppingBasket.add(new ProductOrder(product1, 1));

        return Stream.of(
            Arguments.of(firstShoppingBasket, 22F),
                Arguments.of(secondShoppingBasket, 25.6F)
        );
    }

    @ParameterizedTest
    @MethodSource("shoppingBasketProvider")
    public void calculate_its_total(ShoppingBasket shoppingBasket, float total) {
        assertEquals(total, shoppingBasket.total());
    }
}
