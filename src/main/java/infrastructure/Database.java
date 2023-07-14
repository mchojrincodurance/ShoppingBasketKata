package infrastructure;

import com.codurance.shoppingbasket.model.Product;

import java.util.Arrays;

public class Database {
    private final Product[] products = {
            new Product(10001, "Lord of the Rings", 10),
            new Product(10002, "The Hobbit", 5),
            new Product(20001, "Game of Thrones", 9),
            new Product(20110, "Breaking Bad", 7),
    };

    public Object findBy(String ObjectType, String field, String value) {
        if (ObjectType.equals("product")) {

            if (field.equals("name")) {

                return Arrays
                        .stream(products)
                        .filter((Product element) -> element.name().equals(value))
                        .findFirst()
                        ;
            }
        }

        throw new UnsupportedOperationException("infrastructure.Database::findBy not implemented yet");
    }
}
