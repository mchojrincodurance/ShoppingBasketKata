package com.codurance.shoppingbasket.infrastructure;

import com.codurance.shoppingbasket.model.ShoppingBasket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ShoppingBasketRendererShould {

    private static final int ONWER_ID = 1;
    @Mock
    private MyConsole console;
    @InjectMocks
    private ShoppingBasketRenderer renderer;

    @Test
    public void render_shopping_basket_to_the_console () {
        Date today = new Date();
        ShoppingBasket shoppingBasket = new ShoppingBasket(ONWER_ID, today);

        renderer.render(shoppingBasket);

        // @todo Add InOrder
        verify(console).printLine("Creation date of the shopping basket: 2023-07-16");
        verify(console).printLine("2 x The Hobbit   // 2 x 5.00 = £10.00");
        verify(console).printLine("5 x Breaking Bad // 5 x 7.00 = £35.00");
        verify(console).printLine("Total: £45.00");
    }
}
