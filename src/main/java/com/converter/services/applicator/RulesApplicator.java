package com.converter.services.applicator;

import com.converter.exception.ServiceException;
import com.converter.model.ConversionRequest;

public interface RulesApplicator {

    ConversionRequest applyCurrencyRules(ConversionRequest conversionRequest) throws ServiceException;

}
