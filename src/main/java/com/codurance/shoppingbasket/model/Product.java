package com.codurance.shoppingbasket.model;

public class Product {
    private final int id;
    private final String name;
    private final float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int id() {

        return id;
    }

    public String name()
    {
        return name;
    }

    public float price()
    {
        return price;
    }
}
