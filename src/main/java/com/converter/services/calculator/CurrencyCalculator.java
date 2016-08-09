package com.converter.services.calculator;

import com.converter.model.ConversionResponse;

public interface CurrencyCalculator {

    ConversionResponse calculateCurrency(ConversionResponse conversionResponses);

}
