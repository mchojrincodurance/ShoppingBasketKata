package com.codurance.shoppingbasket.infrastructure;

import java.time.LocalDate;

public class MyClock {
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
