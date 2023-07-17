package com.codurance.shoppingbasket.repositories;

import com.codurance.shoppingbasket.infrastructure.Database;
import com.codurance.shoppingbasket.model.Product;

import java.util.HashMap;

public class ProductRepository {
    private final Database database;

    public ProductRepository(Database database) {

        this.database = database;
    }

    public Product find(String productName) {
        HashMap<String, String> record = database.findBy(Database.PRODUCT, Database.NAME, productName);

        return new Product(
                Integer.parseInt(record.get(Database.ID)),
                record.get(Database.NAME),
                Float.parseFloat(record.get(Database.PRICE))
        );
    }

    public Product find(int itemId) {
        HashMap<String, String> record = database.findBy(Database.PRODUCT, Database.ID, Integer.toString(itemId));

        return new Product(
                Integer.parseInt(record.get(Database.ID)),
                record.get(Database.NAME),
                Float.parseFloat(record.get(Database.PRICE))
        );
    }
}
