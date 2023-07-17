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
    public static final String LORD_OF_THE_RINGS_ID = "10001";
    public static final String LORD_OF_THE_RINGS_NAME = "Lord of the Rings";
    public static final String LORD_OF_THE_RINGS_PRICE = "10";
    public static final String THE_HOBBIT_ID = "10002";
    public static final String THE_HOBBIT_NAME = "The Hobbit";
    public static final String THE_HOBBIT_PRICE = "5";
    public static final String GAME_OF_THRONES_ID = "20001";
    public static final String GAME_OF_THRONES_NAME = "Game of Thrones";
    public static final String GAME_OF_THRONES_PRICE = "9";
    public static final String BREAKING_BAD_ID = "20110";
    public static final String BREAKING_BAD_NAME = "Breaking Bad";
    public static final String BREAKING_BAD_PRICE = "7";
    private int nextObjectId = 1;

    private final HashMap<String, HashMap<String, HashMap<String, String>>> records = new HashMap<>() {{
        put(PRODUCT, new HashMap<>() {{
            put(LORD_OF_THE_RINGS_ID, new HashMap<>() {{
                put(ID, LORD_OF_THE_RINGS_ID);
                put(NAME, LORD_OF_THE_RINGS_NAME);
                put(PRICE, LORD_OF_THE_RINGS_PRICE);
            }});
            put(THE_HOBBIT_ID, new HashMap<>() {{
                put(ID, THE_HOBBIT_ID);
                put(NAME, THE_HOBBIT_NAME);
                put(PRICE, THE_HOBBIT_PRICE);
            }});
            put(GAME_OF_THRONES_ID, new HashMap<>() {{
                put(ID, GAME_OF_THRONES_ID);
                put(NAME, GAME_OF_THRONES_NAME);
                put(PRICE, GAME_OF_THRONES_PRICE);
            }});
            put(BREAKING_BAD_ID, new HashMap<>() {{
                put(ID, BREAKING_BAD_ID);
                put(NAME, BREAKING_BAD_NAME);
                put(PRICE, BREAKING_BAD_PRICE);
            }});
        }});

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

    public int insert(String objectType, HashMap<String, String> assignements) {
        records.get(objectType)
                .put(Integer.toString(nextObjectId++), assignements);

        return nextObjectId;
    }
}
