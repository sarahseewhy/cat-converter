package com.converter.services.calculator;

import com.converter.model.ConversionResponse;
import com.converter.services.AbstractConversionProcess;

import java.math.BigDecimal;
import java.util.List;

import static com.converter.util.CatCurrency.valueOf;
import static java.math.BigDecimal.ZERO;

public class CurrencyCalculatorImpl extends AbstractConversionProcess implements CurrencyCalculator {

    @Override
    public ConversionResponse calculateCurrency(ConversionResponse conversionResponse) {

        final List<BigDecimal> arabicNumbers = conversionResponse.getArabicNumber();
        final List<String> catCurrency = conversionResponse.getCatCurrency();

        BigDecimal currencyTotal = sumArabicNumbers(arabicNumbers);

        if (containsCreditWord(catCurrency)) {
            String creditWord = extractCreditWord(catCurrency);
            currencyTotal = multiplyCreditByCurrency(creditWord, currencyTotal);
        }

        conversionResponse.setResult(currencyTotal);

        return conversionResponse;
    }

    private String extractCreditWord(List<String> catCurrency) {
        return catCurrency.stream().filter(this::isCreditWord).findAny().get();
    }

    private BigDecimal sumArabicNumbers(List<BigDecimal> values) {
        return values.stream().reduce(ZERO, BigDecimal::add);
    }

    private BigDecimal multiplyCreditByCurrency(String creditWord, BigDecimal currencyTotal) {
        return valueOf(creditWord.toUpperCase()).numericValue().multiply(currencyTotal);
    }

    private boolean containsCreditWord(List<String> catCurrency) {
        return catCurrency.stream().anyMatch(this::isCreditWord);
    }
}
