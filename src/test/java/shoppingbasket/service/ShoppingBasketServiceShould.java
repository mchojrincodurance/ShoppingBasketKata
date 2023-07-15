package shoppingbasket.service;

import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.model.ShoppingBasketFactory;
import com.codurance.shoppingbasket.service.ShoppingBasketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ShoppingBasketServiceShould {
    public static final int USER_ID = 1;
    public static final int ITEM_ID = 1;
    public static final String ITEM_NAME = "A product";
    @Spy
    private ShoppingBasketFactory shoppingBasketFactory;
    @InjectMocks
    private ShoppingBasketService shoppingBasketService;

    @Test
    public void create_shopping_basket_when_first_product_is_added() {
        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID);
        assertNull(shoppingBasket);

        shoppingBasketService.addItem(USER_ID, ITEM_ID, 1);
        verify(shoppingBasketFactory, times(1)).create(USER_ID);
    }

    @Test
    public void create_different_shopping_baskets_for_different_users() {
        shoppingBasketService = new ShoppingBasketService(shoppingBasketFactory);

        shoppingBasketService.addItem(1, 1, 2);
        shoppingBasketService.addItem(2, 1, 2);

        ShoppingBasket firstUserBasket = shoppingBasketService.basketFor(1);
        ShoppingBasket secondUserBasket = shoppingBasketService.basketFor(2);

        assertNotEquals(firstUserBasket, secondUserBasket);
    }
}
