package com.codurance.shoppingbasket.repositories;

import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.infrastructure.Database;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Database database;

    public ProductRepository(Database database) {

        this.database = database;
    }

    public Product find(String productName) {
        Map.Entry<String, HashMap<String, String>> record = database.findBy(Database.PRODUCT, Database.NAME, productName);

        return new Product(
                Integer.parseInt(record.getKey()),
                record.getValue().get(Database.NAME),
                Float.parseFloat(record.getValue().get(Database.PRICE))
        );
    }

    public Product find(int itemId) {
        Map.Entry<String, HashMap<String, String>> record = database.findBy(Database.PRODUCT, Database.ID, Integer.toString(itemId));

        return new Product(
                Integer.parseInt(record.getKey()),
                record.getValue().get(Database.NAME),
                Float.parseFloat(record.getValue().get(Database.PRICE))
        );
    }
}
