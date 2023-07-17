package com.codurance.shoppingbasket.presentation;

import com.codurance.shoppingbasket.infrastructure.MyConsole;
import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.model.ShoppingBasket;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import com.codurance.shoppingbasket.model.ProductOrder;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ShoppingBasketConsoleRendererShould {

    private static final int OWNER_ID = 1;
    public static final String THE_HOBBIT = "The Hobbit";
    public static final int THE_HOBBIT_ID = 1;
    public static final int THE_HOBBIT_PRICE = 5;
    public static final String PURCHASE_DATE = "2023-07-16";
    public static final int THE_HOBBIT_ORDERED_QUANTITY = 2;
    public static final int BREAKING_BAD_ID = 2;
    public static final String BREAKING_BAD = "Breaking Bad";
    public static final int BREAKING_BAD_PRICE = 7;
    public static final int BREAKING_BAD_ORDERED_QUANTITY = 5;
    public static final int OTHER_OWNER_ID = 2;
    public static final String OTHER_PURCHASE_DATE = "2015-11-03";
    public static final int ALICE_IN_WONDERLAND_ID = 3;
    public static final String ALICE_IN_WONDERLAND = "Alice in Wonderland";
    public static final float ALICE_IN_WONDERLAND_PRICE = 12.8F;
    public static final int ALICE_IN_WONDERLAND_ORDERED_QUANTITY = 1;
    @Spy
    private MyConsole console;

    @InjectMocks
    private ShoppingBasketConsoleRenderer renderer;

    public static Stream<Arguments> shoppingBasketProvider() {
        Product theHobbit = new Product(THE_HOBBIT_ID, THE_HOBBIT, THE_HOBBIT_PRICE);

        ShoppingBasket firstShoppingBasket = new ShoppingBasket(OWNER_ID, LocalDate.parse(PURCHASE_DATE));
        firstShoppingBasket.add(new ProductOrder(theHobbit, THE_HOBBIT_ORDERED_QUANTITY));

        Product breakingBad = new Product(BREAKING_BAD_ID, BREAKING_BAD, BREAKING_BAD_PRICE);
        firstShoppingBasket.add(new ProductOrder(breakingBad, BREAKING_BAD_ORDERED_QUANTITY));

        Product aliceInWonderland = new Product(ALICE_IN_WONDERLAND_ID, ALICE_IN_WONDERLAND, ALICE_IN_WONDERLAND_PRICE);

        ShoppingBasket secondShoppingBasket = new ShoppingBasket(OTHER_OWNER_ID, LocalDate.parse(OTHER_PURCHASE_DATE));
        secondShoppingBasket.add(new ProductOrder(aliceInWonderland, ALICE_IN_WONDERLAND_ORDERED_QUANTITY));
        secondShoppingBasket.add(new ProductOrder(breakingBad, BREAKING_BAD_ORDERED_QUANTITY + 1));

        return Stream.of(
                Arguments.of(firstShoppingBasket, new String[]{
                        "Creation date of the shopping basket: 2023-07-16",
                        "2 x The Hobbit // 2 x 5.00 = £10.00",
                        "5 x Breaking Bad // 5 x 7.00 = £35.00",
                        "Total: £45.00"
                }),
                Arguments.of(secondShoppingBasket, new String[]{
                        "Creation date of the shopping basket: 2015-11-03",
                        "1 x Alice in Wonderland // 1 x 12.80 = £12.80",
                        "6 x Breaking Bad // 6 x 7.00 = £42.00",
                        "Total: £54.80"
                })
        );
    }

    @ParameterizedTest
    @MethodSource("shoppingBasketProvider")
    public void render_shopping_basket_to_the_console(ShoppingBasket shoppingBasket, String[] output) {
        renderer.render(shoppingBasket);

        // @todo Add InOrder
        for (String line : output) {
            verify(console).printLine(line);
        }
    }
}
