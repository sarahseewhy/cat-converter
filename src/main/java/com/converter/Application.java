package com.converter;

import com.converter.exception.ResourceException;
import com.converter.exception.ServiceException;
import com.converter.model.ConversionRequest;
import com.converter.model.ConversionResponse;
import com.converter.resources.processor.ConversionProcessorImpl;
import com.converter.services.applicator.RulesApplicatorImpl;
import com.converter.services.calculator.CurrencyCalculatorImpl;
import com.converter.services.converter.CurrencyConverterImpl;
import com.converter.services.formatter.InputFormatterImpl;
import com.converter.services.formatter.OutputFormatterImpl;

import java.util.List;

import static com.converter.util.CatConstants.INPUT_PATH;

public class Application {

    public static void main(String[] args) throws ResourceException, ServiceException {

        final InputFormatterImpl inputFormatter = new InputFormatterImpl();
        final RulesApplicatorImpl rulesApplicator = new RulesApplicatorImpl();
        final CurrencyConverterImpl currencyConverter = new CurrencyConverterImpl();
        final CurrencyCalculatorImpl currencyCalculator = new CurrencyCalculatorImpl();
        final OutputFormatterImpl outputFormatter = new OutputFormatterImpl();

        final ConversionProcessorImpl conversionProcessor = new ConversionProcessorImpl();

        final List<ConversionRequest> conversionRequests = conversionProcessor.processRequests(INPUT_PATH);

        for (ConversionRequest conversionRequest : conversionRequests) {
            System.out.println(conversionRequest.getCatCurrency().get(0));

            inputFormatter.formatCatCurrency(conversionRequest);
            rulesApplicator.applyCurrencyRules(conversionRequest);
            ConversionResponse conversionResponse = currencyConverter.convertToArabicNumbers(conversionRequest);
            currencyCalculator.calculateCurrency(conversionResponse);
            outputFormatter.formatConversionResponse(conversionResponse);

            System.out.println(conversionResponse.getCatCurrency().get(0));
        }

    }

}
