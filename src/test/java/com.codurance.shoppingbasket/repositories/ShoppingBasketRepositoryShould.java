package com.codurance.shoppingbasket.repositories;

import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.model.ShoppingBasket;
import infrastructure.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import shoppingbasket.model.ProductOrder;
import shoppingbasket.repositories.ShoppingBasketRepository;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingBasketRepositoryShould
{
    private static final String ITEM_QUANTITY = "10";
    private static final String ITEM_ID = "456";
    private static final String BASKET_ID = "345";
    @Mock
    private Database database;
    private static final String USER_ID = "5";

    @InjectMocks
    private ShoppingBasketRepository shoppingBasketRepository;

    @Test
    public void persist_shopping_baskets_to_database() {
        ShoppingBasket shoppingBasket = new ShoppingBasket(Integer.parseInt(USER_ID));

        shoppingBasket.add(
                new ProductOrder(
                        new Product(Integer.parseInt(ITEM_ID), "Item name", 10),
                        Integer.parseInt(ITEM_QUANTITY)
                )
        );

        when(database.insertShoppingBasket(Integer.parseInt(USER_ID))).thenReturn(Integer.parseInt(BASKET_ID));

        shoppingBasketRepository.save(shoppingBasket);

        verify(database).insertShoppingBasket(Integer.parseInt(USER_ID));
        verify(database).insertProductOrder(Integer.parseInt(BASKET_ID), new HashMap<>() {{
            // BASKET_ID, ITEM_ID, ITEM_QUANTITY
            put("basket_id", BASKET_ID);
            put("item_id", ITEM_ID);
            put("item_quantity", ITEM_QUANTITY);
        }});
    }
}
