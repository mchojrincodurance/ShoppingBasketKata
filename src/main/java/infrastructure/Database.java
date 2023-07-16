package infrastructure;

import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.model.ShoppingBasket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Database {

    private final HashMap<String, ArrayList<Object>> records = new HashMap<>() {{
        put("product", new ArrayList<>(
                        Arrays.asList(
                                new Product(10001, "Lord of the Rings", 10),
                                new Product(10002, "The hobbit", 5),
                                new Product(10002, "Game of Thrones", 9),
                                new Product(10002, "Breaking Bad", 7)
                        )
                )
        );
        put("shopping_basket", new ArrayList<>());
        put("product_order", new ArrayList<>());
    }};

    public Object findBy(String ObjectType, String field, String value) {
        if (field.equals("name")) {

            return records
                    .get(ObjectType)
                    .stream()
                    .filter((element) -> ((Product) element).name().equals(value))
                    .findFirst()
                    .get()
                    ;
        }

        if (field.equals("id")) {
            return records
                    .get(ObjectType)
                    .stream()
                    .filter((element) -> ((Product) element).id() == Integer.parseInt(value))
                    .findFirst()
                    .get()
                    ;
        }

        throw new UnsupportedOperationException("infrastructure.Database::findBy not implemented yet");
    }

    public void insertProductOrder(int shoppingBasketId, HashMap<String, String> fieldValues) {
        records.get("product_order").add(fieldValues);
    }

    public int insertShoppingBasket(int ownerId) {
        records.get("shopping_basket").add(new ShoppingBasket(ownerId));

        return 1;
    }
}
