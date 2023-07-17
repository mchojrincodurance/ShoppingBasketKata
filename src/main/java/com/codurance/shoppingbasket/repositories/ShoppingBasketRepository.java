package com.codurance.shoppingbasket.repositories;

import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.infrastructure.Database;
import com.codurance.shoppingbasket.model.ProductOrder;

import java.util.HashMap;

public class ShoppingBasketRepository {
    private final Database database;

    public ShoppingBasketRepository(Database database)
    {
        this.database = database;
    }

    public void save(ShoppingBasket shoppingBasket) {
        int basketId = database.insert(Database.SHOPPING_BASKET, mapForDatabase(shoppingBasket));

        for(ProductOrder po: shoppingBasket.productOrders()) {
            save(basketId, po);
        }
    }

    private void save(int basketId, ProductOrder po) {
        HashMap<String, String> mappedPo = mapForDatabase(po);
        mappedPo.put(Database.BASKET_ID, Integer.toString(basketId));
        database.insert(Database.PRODUCT_ORDER, mappedPo);
    }

    private static HashMap<String, String> mapForDatabase(ShoppingBasket shoppingBasket) {
        return new HashMap<>() {{
            put(Database.OWNER_ID, Integer.toString(shoppingBasket.ownerId()));
            put(Database.CREATED_AT, shoppingBasket.createdAt().toString());
        }};
    }

    private static HashMap<String, String> mapForDatabase(ProductOrder po) {
        return new HashMap<>() {{
            put(Database.ITEM_ID, Integer.toString(po.product().id()));
            put(Database.ITEM_QUANTITY, Integer.toString(po.quantity()));
        }};
    }
}
