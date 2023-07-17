package com.codurance.shoppingbasket.presentation;

import com.codurance.shoppingbasket.infrastructure.MyConsole;
import com.codurance.shoppingbasket.model.ShoppingBasket;
import shoppingbasket.model.ProductOrder;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ShoppingBasketRenderer {
    private final MyConsole console;

    public ShoppingBasketRenderer(MyConsole console) {
        this.console = console;
    }

    public void render(ShoppingBasket shoppingBasket) {
        console.printLine("Creation date of the shopping basket: " + shoppingBasket.createdAt().toString());

        for (ProductOrder po : shoppingBasket.productOrders() ) {
            render(po);
        }

        console.printLine("Total: £" + formatFloat(shoppingBasket.total()));
    }

    private void render(ProductOrder po) {
        console.printLine(po.quantity() + " x " + po.product().name() + " // " + po.quantity() + " x " + formatFloat(po.product().price()) + " = £" + formatFloat(po.subtotal()));
    }

    private String formatFloat(float value)
    {
        DecimalFormatSymbols decimalSymbols = DecimalFormatSymbols.getInstance();
        decimalSymbols.setDecimalSeparator('.');

        return new DecimalFormat("0.00", decimalSymbols).format(value);
    }
}
