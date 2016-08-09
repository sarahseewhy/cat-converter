package com.converter.services.converter;

import com.converter.model.ConversionRequest;
import com.converter.model.ConversionResponse;

public interface CurrencyConverter {

    ConversionResponse convertToArabicNumbers(ConversionRequest conversionRequest);

}
