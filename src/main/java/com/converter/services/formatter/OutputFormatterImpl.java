package com.converter.services.formatter;

import com.converter.model.ConversionResponse;
import com.converter.services.AbstractConversionProcess;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.converter.util.CatConstants.*;

public class OutputFormatterImpl extends AbstractConversionProcess implements OutputFormatter {

    @Override
    public ConversionResponse formatConversionResponse(ConversionResponse conversionResponse) {

        final List<String> catCurrency = conversionResponse.getCatCurrency();
        final BigDecimal result = conversionResponse.getResult();

        formatCatCurrency(conversionResponse, catCurrency, result);

        return conversionResponse;
    }

    private void formatCatCurrency(ConversionResponse conversionResponse, List<String> catCurrency,
                    BigDecimal result) {

        final List<String> formattedCatCurrency = new ArrayList<>();
        String concatenatedCatCurrency;

        if (!catCurrency.contains(INVALID_INPUT_RESPONSE)) {
            concatenatedCatCurrency = convertToSingleSentence(catCurrency, result);
            formattedCatCurrency.add(concatenatedCatCurrency);
            conversionResponse.setCatCurrency(formattedCatCurrency);
        }
    }

    private String convertToSingleSentence(List<String> catCurrency, BigDecimal result) {

        final StringBuilder singleSentence = new StringBuilder();

        catCurrency.stream().forEach(element -> singleSentence.append(element).append(SPACE));

        addIsVerbTense(singleSentence);

        singleSentence.append(result.intValue());

        appendCreditWord(catCurrency, singleSentence);

        return singleSentence.toString();
    }

    private void appendCreditWord(List<String> catCurrency, StringBuilder singleSentence) {
        if (containsCreditWord(catCurrency)) {
            singleSentence.append(CREDITS);
        }
    }

    private void addIsVerbTense(StringBuilder singleSentence) {
        singleSentence.append(IS);
    }

    private boolean containsCreditWord(List<String> catCurrency) {
        return catCurrency.stream().anyMatch(this::isCreditWord);
    }

}
