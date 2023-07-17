package com.codurance.shoppingbasket.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingBasketShould {

    public static final int OWNER_ID = 1;
    public static final int OTHER_OWNER_ID = 2;
    public static final String FIRST_PRODUCT_NAME = "Product 1";
    public static final int FIRST_PRODUCT_PRICE = 10;
    private static final int FIRST_PRODUCT_ID = 1;
    public static final int FIRST_PRODUCT_FIRST_BASKET_QUANTITY = 2;
    public static final String SECOND_PRODUCT_NAME = "Product 2";
    private static final int SECOND_PRODUCT_ID = 2;
    private static final int SECOND_PRODUCT_FIRST_BASKET_QUANTITY = 5;
    private static final float SECOND_PRODUCT_PRICE = 10.5F;
    public static final int THIRD_PRODUCT_ID = 3;
    public static final String THIRD_PRODUCT_NAME = "Product 3";
    public static final float THIRD_PRODUCT_PRICE = 15.6F;
    private static final int FIRST_PRODUCT_SECOND_BASKET_QUANTITY = 3;
    private static final int THIRD_PRODUCT_SECOND_BASKET_QUANTITY = 4;

    public static Stream<Arguments> shoppingBasketProvider() {
        Product firstProduct = new Product(FIRST_PRODUCT_ID, FIRST_PRODUCT_NAME, FIRST_PRODUCT_PRICE);
        Product secondProduct = new Product(SECOND_PRODUCT_ID, SECOND_PRODUCT_NAME, SECOND_PRODUCT_PRICE);
        Product thirdProduct = new Product(THIRD_PRODUCT_ID, THIRD_PRODUCT_NAME, THIRD_PRODUCT_PRICE);

        ShoppingBasket firstShoppingBasket = new ShoppingBasket(OWNER_ID, LocalDate.now());
        firstShoppingBasket.add(firstProduct, FIRST_PRODUCT_FIRST_BASKET_QUANTITY);
        firstShoppingBasket.add(secondProduct, SECOND_PRODUCT_FIRST_BASKET_QUANTITY);

        ShoppingBasket secondShoppingBasket = new ShoppingBasket(OTHER_OWNER_ID, LocalDate.now());
        secondShoppingBasket.add(firstProduct, FIRST_PRODUCT_SECOND_BASKET_QUANTITY);
        secondShoppingBasket.add(thirdProduct, THIRD_PRODUCT_SECOND_BASKET_QUANTITY);

        return Stream.of(
            Arguments.of(firstShoppingBasket, FIRST_PRODUCT_FIRST_BASKET_QUANTITY * FIRST_PRODUCT_PRICE + SECOND_PRODUCT_FIRST_BASKET_QUANTITY * SECOND_PRODUCT_PRICE),
                Arguments.of(secondShoppingBasket, FIRST_PRODUCT_SECOND_BASKET_QUANTITY * FIRST_PRODUCT_PRICE + THIRD_PRODUCT_SECOND_BASKET_QUANTITY * THIRD_PRODUCT_PRICE)
        );
    }

    @ParameterizedTest
    @MethodSource("shoppingBasketProvider")
    public void calculate_its_total(ShoppingBasket shoppingBasket, float total) {
        assertEquals(total, shoppingBasket.total());
    }
}
