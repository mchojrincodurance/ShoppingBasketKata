package com.codurance.shoppingbasket.infrastructure;

import java.util.HashMap;
import java.util.Map;

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

    private final HashMap<String, HashMap<String, HashMap<String, String>>> records = new HashMap<>() {{
        put(PRODUCT, new HashMap<>());
        put(SHOPPING_BASKET, new HashMap<>());
        put(PRODUCT_ORDER, new HashMap<>());
    }};

    public Map.Entry<String, HashMap<String, String>> findBy(String ObjectType, String field, String value) {
        return records
                .get(ObjectType)
                .entrySet()
                .stream()
                .filter((element) -> (element.getValue().get(field).equals(value)))
                .findFirst()
                .get()
                ;
    }

    public int insert(String objectType, HashMap<String, String> assignments) {
        String id = assignments.containsKey(ID) ? assignments.get(ID) : Integer.toString(nextObjectId++);

        records
                .get(objectType)
                .put(id, assignments);

        return Integer.parseInt(id);
    }
}
