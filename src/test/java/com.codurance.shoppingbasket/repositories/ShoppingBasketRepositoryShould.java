package com.codurance.shoppingbasket.repositories;

import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.infrastructure.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.codurance.shoppingbasket.model.ProductOrder;

import java.time.LocalDate;
import java.util.HashMap;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingBasketRepositoryShould {
    private static final String ITEM_QUANTITY = "10";
    private static final String PRODUCT_ID = "456";
    private static final String BASKET_ID = "345";
    public static final String PRODUCT_NAME = "Item name";
    public static final int PRODUCT_PRICE = 10;
    @Mock
    private Database database;
    private static final String USER_ID = "5";

    @InjectMocks
    private ShoppingBasketRepository shoppingBasketRepository;

    @Test
    public void persist_shopping_baskets_to_database() {
        LocalDate now = buildLocalDate();
        ShoppingBasket shoppingBasket = buildShoppingBasket(now);
        shoppingBasket.add(buildProductOrder());

        when(
                database.insert(Database.SHOPPING_BASKET, buildShoppingBasketData(now))
        )
                .thenReturn(Integer.valueOf(BASKET_ID));

        shoppingBasketRepository.save(shoppingBasket);

        verify(database).insert(Database.SHOPPING_BASKET, buildShoppingBasketData(now));
        verify(database).insert(Database.PRODUCT_ORDER, buildProductOrderData());
    }

    private static LocalDate buildLocalDate() {
        return LocalDate.now();
    }

    private static ShoppingBasket buildShoppingBasket(LocalDate now) {
        return new ShoppingBasket(Integer.parseInt(USER_ID), now);
    }

    private static HashMap<String, String> buildProductOrderData() {
        return new HashMap<>() {{
            put(Database.BASKET_ID, BASKET_ID);
            put(Database.ITEM_ID, PRODUCT_ID);
            put(Database.ITEM_QUANTITY, ITEM_QUANTITY);
        }};
    }

    private static HashMap<String, String> buildShoppingBasketData(LocalDate now) {
        return new HashMap<>() {{
            put(Database.OWNER_ID, USER_ID);
            put(Database.CREATED_AT, now.toString());
        }};
    }

    private static ProductOrder buildProductOrder() {
        return new ProductOrder(
                buildProduct(),
                Integer.parseInt(ITEM_QUANTITY)
        );
    }

    private static Product buildProduct() {
        return new Product(Integer.parseInt(PRODUCT_ID), PRODUCT_NAME, PRODUCT_PRICE);
    }
}
