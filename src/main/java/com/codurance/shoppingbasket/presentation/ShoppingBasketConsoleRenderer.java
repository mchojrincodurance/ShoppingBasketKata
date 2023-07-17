package com.codurance.shoppingbasket.presentation;

import com.codurance.shoppingbasket.infrastructure.MyConsole;
import com.codurance.shoppingbasket.model.ShoppingBasket;
import com.codurance.shoppingbasket.model.ProductOrder;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ShoppingBasketConsoleRenderer {
    private final MyConsole console;

    public ShoppingBasketConsoleRenderer(MyConsole console) {
        this.console = console;
    }

    public void render(ShoppingBasket shoppingBasket) {
        printHeading(shoppingBasket);
        printBody(shoppingBasket);
        printTotal(shoppingBasket);
    }

    private void printTotal(ShoppingBasket shoppingBasket) {
        console.printLine("Total: £" + formatFloat(shoppingBasket.total()));
    }

    private void printBody(ShoppingBasket shoppingBasket) {
        shoppingBasket
                .productOrders()
                .forEach(this::render);
    }

    private void printHeading(ShoppingBasket shoppingBasket) {
        console.printLine("Creation date of the shopping basket: " + shoppingBasket.createdAt().toString());
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
