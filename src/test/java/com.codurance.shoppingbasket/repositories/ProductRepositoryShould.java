package com.codurance.shoppingbasket.repositories;

import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.infrastructure.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryShould {
    public static final String PRODUCT_NAME = "The hobbit";
    public static final String PRODUCT_TYPE = "product";
    public static final float PRODUCT_PRICE = 5F;
    public static final int PRODUCT_ID = 1;
    @Mock
    private Database database;
    @InjectMocks
    private ProductRepository productRepository;

    @Test
    public void find_products_by_name() {
        Product theHobbit = new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE);

        when(database.findBy(PRODUCT_TYPE, Database.NAME, PRODUCT_NAME)).thenReturn(buildProductRecord());

        Product actual = productRepository.find(PRODUCT_NAME);
        assertEquals(theHobbit.id(), actual.id());
        assertEquals(theHobbit.name(), actual.name());
        assertEquals(theHobbit.price(), actual.price());
    }

    @Test
    public void find_products_by_id() {
        Product theHobbit = new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE);

        when(database.findBy(PRODUCT_TYPE, Database.ID, Integer.toString(PRODUCT_ID))).thenReturn(buildProductRecord());

        Product actual = productRepository.find(PRODUCT_ID);
        assertEquals(theHobbit.id(), actual.id());
        assertEquals(theHobbit.name(), actual.name());
        assertEquals(theHobbit.price(), actual.price());
    }

    private static Map.Entry<String, HashMap<String, String>> buildProductRecord() {
        return new Map.Entry<>() {
            @Override
            public String getKey() {
                return Integer.toString(PRODUCT_ID);
            }

            @Override
            public HashMap<String, String> getValue() {
                return new HashMap<>() {{
                    put(Database.NAME, PRODUCT_NAME);
                    put(Database.PRICE, Float.toString(PRODUCT_PRICE));
                }};
            }

            @Override
            public HashMap<String, String> setValue(HashMap<String, String> value) {
                return null;
            }
        };
    }
}
