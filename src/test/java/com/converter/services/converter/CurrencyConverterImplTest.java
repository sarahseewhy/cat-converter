package com.converter.services.converter;

import com.converter.model.ConversionRequest;
import com.converter.model.ConversionResponse;
import com.converter.util.TestUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.converter.util.CatCurrency.*;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CurrencyConverterImplTest extends TestUtil {

    private CurrencyConverterImpl currencyConverter = new CurrencyConverterImpl();

    @Test
    public void testConvertToArabicNumber_convertsCatCurrencyWordMeowToOne() {

        List<String> catCurrency = buildCatCurrency(MEOW.value());
        List<BigDecimal> arabicNumbers = buildArabicNumbers(ONE);

        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);
        ConversionResponse expectedResponse = buildConversionResponse(arabicNumbers, catCurrency, ZERO);

        ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);

        assertThat(conversionResponse.getArabicNumber(), is(expectedResponse.getArabicNumber()));
    }

    @Test
    public void testConvertToArabicNumber_convertsCatCurrencyWordPurrToFive() {

        List<String> catCurrency = buildCatCurrency(PURR.value());
        List<BigDecimal> arabicNumbers = buildArabicNumbers(PURR.numericValue());

        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);
        ConversionResponse expectedResponse = buildConversionResponse(arabicNumbers, catCurrency, ZERO);

        ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);

        assertThat(conversionResponse.getArabicNumber(), is(expectedResponse.getArabicNumber()));
    }

    @Test
    public void testConvertToArabicNumber_convertsCatCurrencyWordHissToTen() {

        List<String> catCurrency = buildCatCurrency(HISS.value());
        List<BigDecimal> arabicNumbers = buildArabicNumbers(HISS.numericValue());

        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);
        ConversionResponse expectedResponse = buildConversionResponse(arabicNumbers, catCurrency, ZERO);

        ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);

        assertThat(conversionResponse.getArabicNumber(), is(expectedResponse.getArabicNumber()));
    }

    @Test
    public void testConvertToArabicNumber_convertsCatCurrencyWordYowlToFifty() {

        List<String> catCurrency = buildCatCurrency(YOWL.value());
        List<BigDecimal> arabicNumbers = buildArabicNumbers(YOWL.numericValue());

        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);
        ConversionResponse expectedResponse = buildConversionResponse(arabicNumbers, catCurrency, ZERO);

        ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);

        assertThat(conversionResponse.getArabicNumber(), is(expectedResponse.getArabicNumber()));
    }

    @Test
    public void testConvertToArabicNumber_convertsConcatenatedMeowPurrToFour() {

        List<String> catCurrency = buildCatCurrency(MEOWPURR.value());
        List<BigDecimal> arabicNumbers = buildArabicNumbers(MEOWPURR.numericValue());

        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);
        ConversionResponse expectedResponse = buildConversionResponse(arabicNumbers, catCurrency, ZERO);

        ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);

        assertThat(conversionResponse.getArabicNumber(), is(expectedResponse.getArabicNumber()));
    }

    @Test
    public void testConvertToArabicNumber_convertsConcatenatedMeowHissToNine() {

        List<String> catCurrency = buildCatCurrency(MEOWHISS.value());
        List<BigDecimal> arabicNumbers = buildArabicNumbers(MEOWHISS.numericValue());

        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);
        ConversionResponse expectedResponse = buildConversionResponse(arabicNumbers, catCurrency, ZERO);

        ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);

        assertThat(conversionResponse.getArabicNumber(), is(expectedResponse.getArabicNumber()));
    }

    @Test
    public void testConvertToArabicNumber_convertsConcatenatedHissYowlToForty() {

        List<String> catCurrency = buildCatCurrency(HISSYOWL.value());
        List<BigDecimal> arabicNumbers = buildArabicNumbers(HISSYOWL.numericValue());

        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);
        ConversionResponse expectedResponse = buildConversionResponse(arabicNumbers, catCurrency, ZERO);

        ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);

        assertThat(conversionResponse.getArabicNumber(), is(expectedResponse.getArabicNumber()));
    }

    @Test
    public void testConvertToArabicNumber_convertsThreeSingleCurrencyWordsToArrayOfArabicNumbers() {

        List<String> catCurrency = buildCatCurrency(MEOW.value(), MEOW.value(), MEOW.value());
        List<BigDecimal> arabicNumbers = buildArabicNumbers(ONE, ONE, ONE);

        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);
        ConversionResponse expectedResponse = buildConversionResponse(arabicNumbers, catCurrency, ZERO);

        ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);

        assertThat(conversionResponse.getArabicNumber(), is(expectedResponse.getArabicNumber()));
    }

    @Test
    public void testConvertToArabicNumber_convertsSingleAndConcatenatedCurrencyWordsToArrayOfArabicNumbers() {

        List<String> catCurrency = buildCatCurrency(HISSYOWL.value(), MEOW.value(), MEOW.value());
        List<BigDecimal> arabicNumbers = buildArabicNumbers(HISSYOWL.numericValue(), ONE, ONE);

        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);
        ConversionResponse expectedResponse = buildConversionResponse(arabicNumbers, catCurrency, ZERO);

        ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);

        assertThat(conversionResponse.getArabicNumber(), is(expectedResponse.getArabicNumber()));

    }

    @Test
    public void testConvertToArabicNumber_convertsMultipleConcatenatedCurrencyWordsToArrayOfArabicNumbers() {

        List<String> catCurrency = buildCatCurrency(HISSYOWL.value(), MEOWPURR.value());
        List<BigDecimal> arabicNumbers = buildArabicNumbers(HISSYOWL.numericValue(), MEOWPURR.numericValue());

        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);
        ConversionResponse expectedResponse = buildConversionResponse(arabicNumbers, catCurrency, ZERO);

        ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);

        assertThat(conversionResponse.getArabicNumber(), is(expectedResponse.getArabicNumber()));

    }

    @Test
    public void testConvertToArabicNumber_ignoresCreditWordsInConversionProcess() {

        List<String> catCurrency = buildCatCurrency(MEOWPURR.value(), MACKEREL.value());
        List<BigDecimal> arabicNumbers = buildArabicNumbers(MEOWPURR.numericValue());

        ConversionRequest conversionRequest = buildConversionRequest(catCurrency);
        ConversionResponse expectedResponse = buildConversionResponse(arabicNumbers, catCurrency, ZERO);

        ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);

        assertThat(conversionResponse.getArabicNumber(), is(expectedResponse.getArabicNumber()));
    }
}
