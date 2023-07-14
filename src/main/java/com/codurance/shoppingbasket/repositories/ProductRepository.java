package com.codurance.shoppingbasket.repositories;

import com.codurance.shoppingbasket.model.Product;
import infrastructure.Database;

public class ProductRepository {
    private final Database database;

    public ProductRepository(Database database) {

        this.database = database;
    }

    public Product find(String productName) {

        return (Product) database.findBy("name", productName);
    }
}
