package com.converter.services.formatter;

import com.converter.model.ConversionResponse;

public interface OutputFormatter {

    ConversionResponse formatConversionResponse(ConversionResponse conversionResponse);

}
