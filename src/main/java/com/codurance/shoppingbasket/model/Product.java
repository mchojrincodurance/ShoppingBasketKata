package com.codurance.shoppingbasket.model;

public class Product {
    private int id;
    private String name;
    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int id() {

        return id;
    }
}
