package com.converter.services.converter;

import com.converter.model.ConversionRequest;
import com.converter.model.ConversionResponse;
import com.converter.services.AbstractConversionProcess;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.converter.util.CatConstants.*;
import static com.converter.util.CatCurrency.valueOf;
import static java.math.BigDecimal.ZERO;

public class CurrencyConverterImpl extends AbstractConversionProcess implements CurrencyConverter {

    @Override
    public ConversionResponse convertToArabicNumbers(ConversionRequest conversionRequest) {

        final List<String> catCurrency = conversionRequest.getCatCurrency();

        final List<BigDecimal> arabicNumbers = new ArrayList<>();

        catCurrency.stream()
                        .filter(word -> !isCreditWord(word))
                        .forEach(currencyWord -> arabicNumbers.add(retrieveNumericValueOf(currencyWord)));

        return generateConversionResponse(catCurrency, arabicNumbers);
    }

    private BigDecimal retrieveNumericValueOf(String currencyWord) {

        BigDecimal numericValue = ZERO;

        if (!currencyWord.equalsIgnoreCase(INVALID_INPUT_RESPONSE)) {
            numericValue = valueOf(currencyWord.toUpperCase().replace(SPACE, EMPTY_SPACE)).numericValue();
        }

        return numericValue;
    }

    private ConversionResponse generateConversionResponse(List<String> catCurrency,
                    List<BigDecimal> arabicNumbers) {
        ConversionResponse conversionResponse = new ConversionResponse();
        conversionResponse.setCatCurrency(catCurrency);
        conversionResponse.setArabicNumber(arabicNumbers);
        return conversionResponse;
    }
}
