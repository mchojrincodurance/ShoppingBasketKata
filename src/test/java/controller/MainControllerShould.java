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

    @Mock
    private ShoppingBasketService shoppingBasketService;

    @Test
    public void allow_adding_items() {
        MainController mainController = new MainController(new MyConsole(), shoppingBasketService);

        mainController.addItem(1, "The hobbit", 2);

        verify(shoppingBasketService).addItem(eq(1), eq(1), eq(2));
    }
}
