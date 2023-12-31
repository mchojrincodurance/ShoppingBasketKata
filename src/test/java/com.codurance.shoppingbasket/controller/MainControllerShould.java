package com.codurance.shoppingbasket.controller;

import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import com.codurance.shoppingbasket.service.ShoppingBasketService;
import com.codurance.shoppingbasket.presentation.ShoppingBasketConsoleRenderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MainControllerShould {

    public static final int USER_ID = 1;
    public static final String FIRST_PRODUCT = "The hobbit";
    public static final int FIRST_PRODUCT_QUANTITY = 2;
    public static final int FIRST_PRODUCT_ID = 1;
    private static final float FIRST_PRODUCT_PRICE = 5;
    public static final String SECOND_PRODUCT = "Breaking Bad";
    public static final int SECOND_PRODUCT_QUANTITY = 5;
    public static final int SECOND_PRODUCT_ID = 2;
    private static final float SECOND_PRODUCT_PRICE = 7;

    @Mock
    private ShoppingBasketService shoppingBasketService;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private MainController mainController;
    @Mock
    private ShoppingBasketConsoleRenderer shoppingBasketRenderer;

    public static Stream<Arguments> provideAddingItemsParameters() {
        return Stream.of(
                Arguments.of(USER_ID, FIRST_PRODUCT, FIRST_PRODUCT_ID, FIRST_PRODUCT_QUANTITY),
                Arguments.of(USER_ID, SECOND_PRODUCT, SECOND_PRODUCT_ID, SECOND_PRODUCT_QUANTITY)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAddingItemsParameters")
    public void allow_adding_items(int userId, String productName, int productId, int quantity) {
        lenient().when(productRepository.find(FIRST_PRODUCT)).thenReturn(new Product(FIRST_PRODUCT_ID, FIRST_PRODUCT, FIRST_PRODUCT_PRICE));
        lenient().when(productRepository.find(SECOND_PRODUCT)).thenReturn(new Product(SECOND_PRODUCT_ID, SECOND_PRODUCT, SECOND_PRODUCT_PRICE));

        mainController.addItem(userId, productName, quantity);

        verify(shoppingBasketService).addItem(eq(userId), eq(productId), eq(quantity));
    }

    @Test
    public void allow_checking_basket_content() {
        ShoppingBasket shoppingBasket = new ShoppingBasket(USER_ID, LocalDate.now());

        when(shoppingBasketService.basketFor(USER_ID)).thenReturn(shoppingBasket);

        mainController.checkBasketContent(USER_ID);

        verify(shoppingBasketRenderer).render(shoppingBasket);
    }
}
