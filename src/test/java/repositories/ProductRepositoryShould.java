package repositories;

import com.codurance.shoppingbasket.model.Product;
import com.codurance.shoppingbasket.repositories.ProductRepository;
import infrastructure.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryShould {
    @Mock
    private Database database;
    @InjectMocks
    private ProductRepository productRepository;

    @Test
    public void find_products_by_name() {
        Product theHobbit = new Product(1, "The hobbit");
        when(database.findBy("name", "The hobbit")).thenReturn(theHobbit);

        assertEquals(theHobbit, productRepository.find("The hobbit"));
    }
}
