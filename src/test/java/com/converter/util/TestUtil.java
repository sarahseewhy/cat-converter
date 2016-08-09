package com.converter.util;

import com.converter.model.ConversionRequest;
import com.converter.model.ConversionResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUtil {

    protected List<String> buildCatCurrency(String... currency) {
        List<String> catCurrency = new ArrayList<>();
        Collections.addAll(catCurrency, currency);
        return catCurrency;
    }

    protected String concatenateCurrency(String reducer, String currencyToBeReducedBy) {
        return reducer + " " + currencyToBeReducedBy;
    }

    protected List<BigDecimal> buildArabicNumbers(BigDecimal... arabicNumbers) {
        List<BigDecimal> expectedConversionResult = new ArrayList<>();
        Collections.addAll(expectedConversionResult, arabicNumbers);
        return expectedConversionResult;
    }

    protected ConversionResponse buildConversionResponse(List<BigDecimal> arabicNumbers, List<String> catCurrency,
                    BigDecimal result) {
        ConversionResponse conversionResponse = new ConversionResponse();
        conversionResponse.setArabicNumber(arabicNumbers);
        conversionResponse.setCatCurrency(catCurrency);
        conversionResponse.setResult(result);
        return conversionResponse;
    }

    protected ConversionRequest buildConversionRequest(List<String> currency) {
        ConversionRequest conversionRequest = new ConversionRequest();
        conversionRequest.setCatCurrency(currency);
        return conversionRequest;
    }
}
