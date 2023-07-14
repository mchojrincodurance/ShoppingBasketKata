package controller;

import com.codurance.shoppingbasket.controller.MainController;
import com.codurance.shoppingbasket.infrastructure.MyConsole;
import com.codurance.shoppingbasket.service.ShoppingBasketService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MainControllerShould {

    public static final int USER_ID = 1;
    public static final String FIRST_PRODUCT = "The hobbit";
    public static final int FIRST_PRODUCT_QUANTITY = 2;
    public static final int FIRST_PRODUCT_ID = 1;
    public static final String SECOND_PRODUCT = "Breaking Bad";
    public static final int SECOND_PRODUCT_QUANTITY = 5;
    public static final int SECOND_PRODUCT_ID = 2;

    private final MyConsole console = new MyConsole();
    @Mock
    private ShoppingBasketService shoppingBasketService;

    public static Stream<Arguments> provideAddingItemsParameters() {
        return Stream.of(
                Arguments.of(USER_ID, FIRST_PRODUCT, FIRST_PRODUCT_ID, FIRST_PRODUCT_QUANTITY),
                Arguments.of(USER_ID, SECOND_PRODUCT, SECOND_PRODUCT_ID, SECOND_PRODUCT_QUANTITY)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAddingItemsParameters")
    public void allow_adding_items(int userId, String productName, int productId, int quantity) {
        MainController mainController = new MainController(console, shoppingBasketService);

        mainController.addItem(userId, productName, quantity);

        verify(shoppingBasketService).addItem(eq(userId), eq(productId), eq(quantity));
    }
}
