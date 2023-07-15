package infrastructure;

import com.codurance.shoppingbasket.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Database {

    private final HashMap<String, ArrayList<Object>> records = new HashMap<String, ArrayList<Object>>() {{
        put("products", new ArrayList<Object>(Arrays.asList(new Product(10001, "Lord of the Rings", 10))));
        put("shopping_baskets", new ArrayList<Object>());
    }};

    public Object findBy(String ObjectType, String field, String value) {
        if (field.equals("name")) {

            return records
                    .get(ObjectType)
                    .stream()
                    .filter((element) -> ((Product)element).name().equals(value))
                    .findFirst();
        }

        throw new UnsupportedOperationException("infrastructure.Database::findBy not implemented yet");
    }
}
