package com.codurance.shoppingbasket.infrastructure;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    public static final String SHOPPING_BASKET = "shopping_basket";
    public static final String CREATED_AT = "created_at";
    public static final String BASKET_ID = "basket_id";
    public static final String ITEM_ID = "item_id";
    public static final String ITEM_QUANTITY = "item_quantity";
    public static final String PRODUCT_ORDER = "product_order";
    public static final String OWNER_ID = "owner_id";
    public static final String PRODUCT = "product";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    private int nextObjectId = 1;

    private final HashMap<String, ArrayList<HashMap<String, String>>> records = new HashMap<>() {{
        put(PRODUCT, new ArrayList<>());
        put(SHOPPING_BASKET, new ArrayList<>());
        put(PRODUCT_ORDER, new ArrayList<>());
    }};

    public HashMap<String, String> findBy(String ObjectType, String field, String value) {
        return records
                .get(ObjectType)
                .stream()
                .filter((element) -> (element.get(field).equals(value)))
                .findFirst()
                .orElse(null);
    }

    public int insert(String objectType, HashMap<String, String> assignments) {
        if (!assignments.containsKey(ID)) {
            assignments.put(ID, Integer.toString(nextObjectId++));
        }

        records
                .get(objectType)
                .add(assignments);

        return Integer.parseInt(assignments.get(ID));
    }
}
