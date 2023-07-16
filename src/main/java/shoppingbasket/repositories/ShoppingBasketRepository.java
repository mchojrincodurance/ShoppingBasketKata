package shoppingbasket.repositories;

import com.codurance.shoppingbasket.model.ShoppingBasket;
import infrastructure.Database;
import shoppingbasket.model.ProductOrder;

import java.util.HashMap;

public class ShoppingBasketRepository {
    private final Database database;

    public ShoppingBasketRepository(Database database)
    {
        this.database = database;
    }

    public void save(ShoppingBasket shoppingBasket) {
        int basketId = database.insertShoppingBasket(shoppingBasket.ownerId());

        for(ProductOrder po: shoppingBasket.productOrders()) {
            database.insertProductOrder(basketId, new HashMap<>() {{
                put("basket_id", Integer.toString(basketId));
                put("item_id", Integer.toString(po.product().id()));
                put("item_quantity", Integer.toString(po.quantity()));
            }});
        }
    }
}
