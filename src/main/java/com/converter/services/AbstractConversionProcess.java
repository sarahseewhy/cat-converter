package com.converter.services;

import static com.converter.util.CatCurrency.*;

public class AbstractConversionProcess {

    protected boolean isCreditWord(String word) {
        return isTuna(word) || isMackerel(word) || isSalmon(word);
    }

    private boolean isSalmon(String word) {
        return word.equals(SALMON.value());
    }

    private boolean isMackerel(String word) {
        return word.equals(MACKEREL.value());
    }

    private boolean isTuna(String word) {
        return word.equals(TUNA.value());
    }
}

