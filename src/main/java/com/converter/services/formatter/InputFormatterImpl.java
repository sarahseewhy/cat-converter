package com.converter.services.formatter;

import com.converter.exception.ServiceException;
import com.converter.model.ConversionRequest;
import com.converter.util.CatCurrency;

import java.util.ArrayList;
import java.util.List;

import static com.converter.util.CatConstants.*;
import static com.converter.util.CatCurrency.values;

public class InputFormatterImpl implements InputFormatter {

    @Override
    public ConversionRequest formatCatCurrency(ConversionRequest conversionRequest) {

        final List<String> formattedCatCurrency = new ArrayList<>();

        String unformattedCatCurrency = conversionRequest.getCatCurrency().get(0);

        String[] normalizedCurrency = removePunctuation(unformattedCatCurrency).split(WORD_PATTERN);

        addCurrencyWords(normalizedCurrency, formattedCatCurrency);

        validateCurrencyWords(formattedCatCurrency);

        conversionRequest.setCatCurrency(formattedCatCurrency);

        return conversionRequest;
    }

    private void addCurrencyWords(String[] normalizedCurrency, List<String> currencyWords) {

        for (String word : normalizedCurrency) {
            if (isCurrencyWord(word)) {
                currencyWords.add(word);
            }
        }
    }

    private String removePunctuation(String input) {
        return input.replaceAll(PUNCTUATION_PATTERN, EMPTY_SPACE);
    }

    private void validateCurrencyWords(List<String> catCurrency) {
        if (0 == catCurrency.size()) {
            catCurrency.add(INVALID_INPUT_RESPONSE);
        }
    }

    private boolean isCurrencyWord(String word) {

        for (CatCurrency catCurrency : values()) {
            if (word.equalsIgnoreCase(catCurrency.value())) {
                return true;
            }
        }

        return false;
    }
}
