package feature;

import com.codurance.shoppingbasket.controller.MainController;
import com.codurance.shoppingbasket.infrastructure.MyConsole;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import com.codurance.shoppingbasket.service.ShoppingBasketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class addItemsFeature {
    @Mock MyConsole console;
    private MainController mainController;

    @Before
    public void setUp() {
        mainController = new MainController(console, new ShoppingBasketService(), new ProductRepository());
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
        mainController.addItem(1, "The hobbit", 2);
        mainController.addItem(1, "Breaking bad", 5);

        mainController.checkBasketContent(1);

        verify(console).printLine("Creation date of the shopping basket: 2023-07-14");
        verify(console).printLine("2 x The Hobbit   // 2 x 5.00 = £10.00");
        verify(console).printLine("5 x Breaking Bad // 5 x 7.00 = £35.00");
        verify(console).printLine("Total: £45.00");
    }
}
