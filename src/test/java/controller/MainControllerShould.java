package controller;

import com.codurance.shoppingbasket.controller.MainController;
import com.codurance.shoppingbasket.infrastructure.MyConsole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import com.codurance.shoppingbasket.service.ShoppingBasketService;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerShould {

    public static final int USER_ID = 1;
    public static final String PRODUCT_NAME = "The hobbit";
    public static final int THE_HOBBIT_QUANTITY = 2;
    public static final int THE_HOBBIT_ID = 1;
    private final MyConsole console = new MyConsole();
    @Mock
    private ShoppingBasketService shoppingBasketService;

    @Test
    public void allow_adding_items() {
        MainController mainController = new MainController(console, shoppingBasketService);

        mainController.addItem(USER_ID, PRODUCT_NAME, THE_HOBBIT_QUANTITY);

        verify(shoppingBasketService).addItem(eq(USER_ID), eq(THE_HOBBIT_ID), eq(THE_HOBBIT_QUANTITY));
    }
}
