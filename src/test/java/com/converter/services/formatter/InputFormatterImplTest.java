package com.converter.services.formatter;

import com.converter.model.ConversionRequest;
import com.converter.util.TestUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static com.converter.util.CatConstants.INVALID_INPUT_RESPONSE;
import static com.converter.util.CatCurrency.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputFormatterImplTest extends TestUtil {

    private InputFormatterImpl inputFormatter = new InputFormatterImpl();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testFormatCatCurrency_createsArrayOfSubStringCurrencyWords() throws Exception {

        String input = "how many Credits is meow purr Tuna ?";

        ConversionRequest conversionRequest = buildConversionRequest(buildCatCurrency(input));

        List<String> expectedCurrencyWords = buildCatCurrency(MEOW.value(), PURR.value(), TUNA.value());

        List<String> catCurrency = inputFormatter.formatCatCurrency(conversionRequest).getCatCurrency();

        assertThat(catCurrency, is(expectedCurrencyWords));
    }

    @Test
    public void testFormatCatCurrency_returnsInvalidInputResponseWhenProvidedInvalidInput() throws Exception {

        String input = "how much wood could a woodchuck chuck if a woodchuck could chuck wood?";
        ConversionRequest conversionRequest = buildConversionRequest(buildCatCurrency(input));
        List<String> expectedCatCurrency = buildCatCurrency(INVALID_INPUT_RESPONSE);

        List<String> catCurrency = inputFormatter.formatCatCurrency(conversionRequest).getCatCurrency();

        assertThat(catCurrency, is(expectedCatCurrency));
    }

}
