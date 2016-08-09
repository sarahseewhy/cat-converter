package com.converter.services.calculator;

import com.converter.model.ConversionResponse;
import com.converter.util.TestUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.converter.util.CatCurrency.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CurrencyCalculatorImplTest extends TestUtil {

    private final BigDecimal valueOfMeow = MEOW.numericValue();
    private final BigDecimal valueOfMeowPurr = MEOWPURR.numericValue();
    private final BigDecimal valueOfHissYowl = HISSYOWL.numericValue();
    private final BigDecimal valueOfMeowMeow = valueOfMeow.add(valueOfMeow);
    private final List<BigDecimal> arabicNumbers = buildArabicNumbers(valueOfMeow, valueOfMeow);
    private final List<String> catCurrency = buildCatCurrency(MEOW.value(), MEOW.value());
    private final String concatenatedHissYowl = concatenateCurrency(HISS.value(), YOWL.value());
    private final String concatenatedMeowPurr = concatenateCurrency(MEOW.value(), PURR.value());
    private final CurrencyCalculatorImpl currencyCalculator = new CurrencyCalculatorImpl();
    private final BigDecimal result = BigDecimal.ZERO;

    @Test
    public void testCalculateResult_addsBigDecimalArrayElementsToCalculateCurrencyResult() {

        ConversionResponse conversionResponse = buildConversionResponse(arabicNumbers, catCurrency, result);

        BigDecimal expectedResult = valueOfMeowMeow;

        ConversionResponse result = currencyCalculator.calculateCurrency(conversionResponse);

        assertThat(result.getResult(), is(expectedResult));

    }

    @Test
    public void testCalculateResult_multipliesCurrencySumByTunaCreditWord() {

        catCurrency.add(TUNA.value());
        ConversionResponse conversionResponse = buildConversionResponse(arabicNumbers, catCurrency, result);
        BigDecimal expectedResult = valueOfMeowMeow.multiply(TUNA.numericValue());

        ConversionResponse result = currencyCalculator.calculateCurrency(conversionResponse);

        assertThat(result.getResult(), is(expectedResult));

    }

    @Test
    public void testCalculateResult_multipliesCurrencySumByMackerelCreditWord() {

        catCurrency.add(MACKEREL.value());
        ConversionResponse conversionResponse = buildConversionResponse(arabicNumbers, catCurrency, result);
        BigDecimal expectedResult = valueOfMeowMeow.multiply(MACKEREL.numericValue());

        ConversionResponse result = currencyCalculator.calculateCurrency(conversionResponse);

        assertThat(result.getResult(), is(expectedResult));

    }

    @Test
    public void testCalculateResult_multipliesCurrencySumBySalmonCreditWord() {

        catCurrency.add(SALMON.value());
        ConversionResponse conversionResponse = buildConversionResponse(arabicNumbers, catCurrency, result);
        BigDecimal expectedResult = valueOfMeowMeow.multiply(SALMON.numericValue());

        ConversionResponse result = currencyCalculator.calculateCurrency(conversionResponse);

        assertThat(result.getResult(), is(expectedResult));

    }

    @Test
    public void testCalculateResult_addsBigDecimalConcatenatedArrayElementsToCalculateCurrencyResult() {

        List<BigDecimal> arabicNumbers = buildArabicNumbers(valueOfHissYowl, valueOfMeowPurr);
        List<String> currencyWords = buildCatCurrency(concatenatedHissYowl, concatenatedMeowPurr);

        ConversionResponse conversionResponse = buildConversionResponse(arabicNumbers, currencyWords, result);
        BigDecimal expectedResult = valueOfHissYowl.add(valueOfMeowPurr);

        ConversionResponse result = currencyCalculator.calculateCurrency(conversionResponse);

        assertThat(result.getResult(), is(expectedResult));

    }

    @Test
    public void testCalculateResult_multipliesConcatenatedCurrencyWordsByCreditWord() {

        List<BigDecimal> arabicNumbers = buildArabicNumbers(valueOfHissYowl, valueOfMeowPurr);
        List<String> currencyWords = buildCatCurrency(concatenatedHissYowl, concatenatedMeowPurr, SALMON.value());

        ConversionResponse conversionResponse = buildConversionResponse(arabicNumbers, currencyWords, result);
        BigDecimal expectedResult = valueOfHissYowl.add(valueOfMeowPurr).multiply(SALMON.numericValue());

        ConversionResponse result = currencyCalculator.calculateCurrency(conversionResponse);

        assertThat(result.getResult(), is(expectedResult));

    }
}
