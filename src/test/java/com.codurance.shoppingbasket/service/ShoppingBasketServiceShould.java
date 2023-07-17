package com.codurance.shoppingbasket.service;

import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.model.ShoppingBasketFactory;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import com.codurance.shoppingbasket.repositories.ShoppingBasketRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingBasketServiceShould {
    public static final int USER_ID = 1;
    public static final int ITEM_ID = 1;
    private static final String ITEM_NAME = "An item";
    private static final int ITEM_QUANTITY = 2;
    private static final int ITEM_PRICE = 6;
    private static final int OTHER_USER_ID = 2;
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
        shoppingBasketService = new ShoppingBasketService(shoppingBasketFactory, shoppingBasketRepository, productRepository);

        shoppingBasketService.addItem(USER_ID, ITEM_ID, 2);
        shoppingBasketService.addItem(OTHER_USER_ID, ITEM_ID, 3);

        ShoppingBasket firstUserBasket = shoppingBasketService.basketFor(USER_ID);
        ShoppingBasket secondUserBasket = shoppingBasketService.basketFor(OTHER_USER_ID);

        assertEquals(0, firstUserBasket.productOrders().stream().filter((elem) -> elem.quantity() == 3).count());
        assertEquals(0, secondUserBasket.productOrders().stream().filter((elem) -> elem.quantity() == 2).count());
    }

    @Test
    public void persist_shopping_baskets() {
        shoppingBasketService.addItem(USER_ID, ITEM_ID, 1);
        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID);

        verify(shoppingBasketRepository).save(shoppingBasket);
    }

    @Test
    public void add_items_to_the_shopping_basket() {
        Product product = new Product(ITEM_ID, ITEM_NAME, ITEM_PRICE);
        when(productRepository.find(ITEM_ID)).thenReturn(product);

        shoppingBasketService.addItem(USER_ID, ITEM_ID, ITEM_QUANTITY);
        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID);

        assertEquals(1, shoppingBasket.
                productOrders()
                .stream()
                .filter((order) -> order.product() == product && order.quantity() == ITEM_QUANTITY)
                .count()
        );
    }
}
