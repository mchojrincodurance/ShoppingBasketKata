package controller;

import com.codurance.shoppingbasket.controller.MainController;
import org.junit.Test;
import org.mockito.Mock;
import com.codurance.shoppingbasket.service.ShoppingBasketService;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class MainControllerShould {

    @Mock
    private ShoppingBasketService shoppingBasketService;

    @Test
    public void allow_adding_items() {
        MainController mainController = new MainController(shoppingBasketService);

        mainController.addItem(1, "The hobbit", 2);

        verify(shoppingBasketService).addItem(eq(1), eq(1), eq(2));
    }
}
