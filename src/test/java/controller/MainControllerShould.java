package controller;

import com.codurance.shoppingbasket.controller.MainController;
import com.codurance.shoppingbasket.infrastructure.MyConsole;
import com.codurance.shoppingbasket.service.ShoppingBasketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MainControllerShould {

    public static final int USER_ID = 1;
    public static final String PRODUCT_NAME = "The hobbit";
    public static final int THE_HOBBIT_QUANTITY = 2;
    public static final int THE_HOBBIT_ID = 1;
    private final MyConsole console = new MyConsole();
    @Mock
    private ShoppingBasketService shoppingBasketService;

    public static Stream<Arguments> provideAddingItemsParameters() {
        return Stream.of(
                Arguments.of(USER_ID, PRODUCT_NAME, THE_HOBBIT_QUANTITY)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAddingItemsParameters")
    public void allow_adding_items(int userId, String productName, int quantity) {
        MainController mainController = new MainController(console, shoppingBasketService);

        mainController.addItem(USER_ID, PRODUCT_NAME, THE_HOBBIT_QUANTITY);

        verify(shoppingBasketService).addItem(eq(USER_ID), eq(THE_HOBBIT_ID), eq(THE_HOBBIT_QUANTITY));
    }
}
