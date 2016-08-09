package com.converter.util;

import java.math.BigDecimal;

public enum CatCurrency {

    MEOW ("meow", BigDecimal.ONE),
    PURR ("purr", BigDecimal.valueOf(5)),
    HISS ("hiss", BigDecimal.valueOf(10)),
    YOWL ("yowl", BigDecimal.valueOf(50)),

    MEOWPURR ("meow purr", BigDecimal.valueOf(4)),
    MEOWHISS ("meow hiss", BigDecimal.valueOf(9)),
    HISSYOWL ("hiss yowl", BigDecimal.valueOf(40)),

    TUNA ("Tuna", BigDecimal.valueOf(195.5)),
    MACKEREL("Mackerel", BigDecimal.valueOf(17)),
    SALMON("Salmon", BigDecimal.valueOf(14450));

    private final String value;
    private final BigDecimal numericValue;

    public String value() {
        return value;
    }

    public BigDecimal numericValue() {
        return numericValue;
    }

    CatCurrency(String value, BigDecimal numericValue) {
        this.value = value;
        this.numericValue = numericValue;
    }
}
