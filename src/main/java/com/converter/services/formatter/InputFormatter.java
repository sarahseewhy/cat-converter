package com.converter.services.formatter;

import com.converter.exception.ServiceException;
import com.converter.model.ConversionRequest;

public interface InputFormatter {

    ConversionRequest formatCatCurrency(ConversionRequest conversionRequest) throws ServiceException;

}
