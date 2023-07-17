package com.codurance.shoppingbasket.model;

import com.codurance.shoppingbasket.infrastructure.MyClock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingBasketFactoryShould {
    @Mock
    MyClock clock;

    @InjectMocks
    private ShoppingBasketFactory shoppingBasketFactory;

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    public void create_baskets_for_users(int userId) {
        ShoppingBasket createdBasket = shoppingBasketFactory.create(userId);
        assertEquals(userId, createdBasket.ownerId());
    }

    @ParameterizedTest
    @CsvSource({
            "2022-12-22",
            "2023-02-01"
    })
    public void keep_track_of_baskets_creation_date(String dateString) {
        LocalDate date = LocalDate.parse(dateString);

        when(clock.getCurrentDate()).thenReturn(date);

        ShoppingBasket createdBasket = shoppingBasketFactory.create(1);

        assertEquals(date, createdBasket.createdAt());
    }
}
