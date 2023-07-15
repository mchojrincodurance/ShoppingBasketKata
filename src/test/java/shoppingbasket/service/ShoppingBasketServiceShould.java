package shoppingbasket.service;

import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.service.ShoppingBasketService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ShoppingBasketServiceShould {
    public static final int USER_ID = 1;
    public static final int ITEM_ID = 1;
    public static final String ITEM_NAME = "A product";
    private ShoppingBasketService shoppingBasketService = new ShoppingBasketService();

    @Test
    public void create_shopping_basket_when_first_product_is_added() {
        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID);
        assertNull(shoppingBasket);

        shoppingBasketService.addItem(USER_ID, ITEM_ID, 1);

        shoppingBasket = shoppingBasketService.basketFor(USER_ID);
        assertEquals(USER_ID, shoppingBasket.ownerId());

        Product purchasedProduct = new Product(ITEM_ID, ITEM_NAME, 2);
        assertEquals(purchasedProduct, shoppingBasket.products().stream().findFirst());
        assertEquals(1, shoppingBasket.products().size());
    }
}
