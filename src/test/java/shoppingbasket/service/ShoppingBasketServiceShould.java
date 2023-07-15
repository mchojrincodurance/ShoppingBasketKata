package shoppingbasket.service;

import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.model.ShoppingBasketFactory;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import com.codurance.shoppingbasket.service.ShoppingBasketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import shoppingbasket.model.ProductOrder;
import shoppingbasket.repositories.ShoppingBasketRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingBasketServiceShould {
    public static final int USER_ID = 1;
    public static final int ITEM_ID = 1;
    private static final String ITEM_NAME = "An item";
    private static final int ITEM_QUANTITY = 2;
    private static final int ITEM_PRICE = 6;
    @Spy
    private ShoppingBasketFactory shoppingBasketFactory;
    @InjectMocks
    private ShoppingBasketService shoppingBasketService;
    @Mock
    private ShoppingBasketRepository shoppingBasketRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void create_shopping_basket_when_first_product_is_added() {
        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID);
        assertNull(shoppingBasket);

        shoppingBasketService.addItem(USER_ID, ITEM_ID, 1);
        verify(shoppingBasketFactory, times(1)).create(USER_ID);
    }

    @Test
    public void create_different_shopping_baskets_for_different_users() {
        shoppingBasketService = new ShoppingBasketService(shoppingBasketFactory, shoppingBasketRepository,productRepository );

        shoppingBasketService.addItem(1, 1, 2);
        shoppingBasketService.addItem(2, 1, 2);

        ShoppingBasket firstUserBasket = shoppingBasketService.basketFor(1);
        ShoppingBasket secondUserBasket = shoppingBasketService.basketFor(2);

        assertNotEquals(firstUserBasket, secondUserBasket);
    }

    @Test
    public void persist_shopping_baskets() {
        shoppingBasketService.addItem(USER_ID, ITEM_ID, 1);
        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID);

        verify(shoppingBasketRepository).save(shoppingBasket);
    }

    @Test
    public void add_items_to_the_shopping_basket() {
        shoppingBasketService.addItem(USER_ID, ITEM_ID, ITEM_QUANTITY);
        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID);

        Product product = new Product(ITEM_ID, ITEM_NAME, ITEM_PRICE);

        when(productRepository.find(ITEM_ID)).thenReturn(product);

        ProductOrder productOrder = new ProductOrder(product, ITEM_QUANTITY);

        assertTrue(shoppingBasket.productOrders().contains(productOrder));
    }
}
