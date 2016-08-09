package com.converter.services;

import static com.converter.util.CatCurrency.*;

public class AbstractConversionProcess {

    protected boolean isCreditWord(String word) {
        return isTuna(word) || isMackerel(word) || isSalmon(word);
    }

    protected boolean isSalmon(String word) {
        return word.equals(SALMON.value());
    }

    protected boolean isMackerel(String word) {
        return word.equals(MACKEREL.value());
    }

    protected boolean isTuna(String word) {
        return word.equals(TUNA.value());
    }
}

