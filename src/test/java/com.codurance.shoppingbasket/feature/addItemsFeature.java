package com.codurance.shoppingbasket.feature;

import com.codurance.shoppingbasket.controller.MainController;
import com.codurance.shoppingbasket.infrastructure.MyClock;
import com.codurance.shoppingbasket.infrastructure.MyConsole;
import com.codurance.shoppingbasket.presentation.ShoppingBasketRenderer;
import com.codurance.shoppingbasket.model.ShoppingBasketFactory;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import com.codurance.shoppingbasket.service.ShoppingBasketService;
import infrastructure.Database;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingbasket.repositories.ShoppingBasketRepository;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class addItemsFeature {
    public static final String THE_HOBBIT = "The Hobbit";
    public static final String BREAKING_BAD = "Breaking Bad";
    public static final int THE_HOBBIT_QUANTITY = 2;
    public static final int BREAKING_BAD_QUANTITY = 5;
    public static final int USER_ID = 1;
    private final Database database = new Database();
    private final ProductRepository productRepository = new ProductRepository(database);
    private final ShoppingBasketRepository shoppingBasketRepository = new ShoppingBasketRepository(database);
    @Mock
    private MyClock clock;
    @InjectMocks
    private ShoppingBasketFactory shoppingBasketFactory;
    @Mock
    MyConsole console;
    @InjectMocks
    private ShoppingBasketRenderer shoppingBasketRenderer;
    private MainController mainController;

    @Before
    public void setUp() {
        mainController = new MainController(shoppingBasketRenderer, new ShoppingBasketService(
                shoppingBasketFactory,
                shoppingBasketRepository,
                productRepository
        ), productRepository);
    }

    /**
     * Given I add 2 units of "The Hobbit" to my shopping basket
     * And I add 5 units of "Breaking Bad"
     * When I check the content of my shopping basket
     * Then it should contain the following information:
     * - Creation date of the shopping basket
     * - 2 x The Hobbit   // 2 x 5.00 = £10.00
     * - 5 x Breaking Bad // 5 x 7.00 = £35.00
     * - Total: £45.00
     */

    @Test
    public void print_basket_contents_with_total() {
        when(clock.getCurrentDate()).thenReturn(LocalDate.parse("2023-07-14"));

        mainController.addItem(USER_ID, THE_HOBBIT, THE_HOBBIT_QUANTITY);
        mainController.addItem(USER_ID, BREAKING_BAD, BREAKING_BAD_QUANTITY);

        mainController.checkBasketContent(USER_ID);

        verify(console).printLine("Creation date of the shopping basket: 2023-07-14");
        verify(console).printLine("2 x The Hobbit // 2 x 5.00 = £10.00");
        verify(console).printLine("5 x Breaking Bad // 5 x 7.00 = £35.00");
        verify(console).printLine("Total: £45.00");
    }
}
