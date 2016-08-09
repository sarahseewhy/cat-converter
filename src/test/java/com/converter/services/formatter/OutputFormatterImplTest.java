package com.converter.services.formatter;

import com.converter.model.ConversionResponse;
import com.converter.util.TestUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.converter.util.CatConstants.*;
import static com.converter.util.CatCurrency.*;
import static java.math.BigDecimal.ONE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OutputFormatterImplTest extends TestUtil {

    private OutputFormatterImpl outputFormatter = new OutputFormatterImpl();

    @Test
    public void testFormatOutputSentences_convertsSingleElementInCatCurrencyToSingleSentence() {

        List<BigDecimal> arabicNumbers = buildArabicNumbers(ONE);
        List<String> catCurrency = buildCatCurrency(MEOW.value());
        ConversionResponse conversionResponse = buildConversionResponse(arabicNumbers, catCurrency, ONE);

        ConversionResponse formattedResponse = outputFormatter.formatConversionResponse(conversionResponse);

        assertThat(formattedResponse.getCatCurrency().get(0), is(MEOW.value() + SPACE + IS + ONE.intValue()));
    }

    @Test
    public void testFormatOutputSentences_convertsMultipleElementsInCatCurrencyToSingleSentence() {

        List<BigDecimal> arabicNumbers = buildArabicNumbers(HISSYOWL.numericValue(), ONE);
        List<String> catCurrency = buildCatCurrency(HISSYOWL.value(), MEOW.value());
        BigDecimal result = ONE.add(HISSYOWL.numericValue());

        ConversionResponse conversionResponse = buildConversionResponse(arabicNumbers, catCurrency, result);

        ConversionResponse formattedResponse = outputFormatter.formatConversionResponse(conversionResponse);

        assertThat(formattedResponse.getCatCurrency().get(0), is("hiss yowl meow is " + result.intValue()));
    }

    @Test
    public void testFormatOutputSentences_addsCreditIndicatorToSentenceWhenCreditWordIsPresent() {

        List<BigDecimal> arabicNumbers = buildArabicNumbers(HISSYOWL.numericValue(), ONE);
        List<String> catCurrency = buildCatCurrency(HISSYOWL.value(), MEOW.value(), TUNA.value());
        BigDecimal result = ONE.add(HISSYOWL.numericValue()).multiply(TUNA.numericValue());
        ConversionResponse conversionResponse = buildConversionResponse(arabicNumbers, catCurrency, result);

        ConversionResponse formattedResponse = outputFormatter.formatConversionResponse(conversionResponse);

        assertThat(formattedResponse.getCatCurrency().get(0), is("hiss yowl meow Tuna " + IS + result.intValue() + CREDITS));

    }
}
