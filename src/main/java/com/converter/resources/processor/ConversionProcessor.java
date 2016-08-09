package com.converter.resources.processor;

import com.converter.exception.ResourceException;
import com.converter.model.ConversionRequest;

import java.util.List;

public interface ConversionProcessor {

    List<ConversionRequest> processRequests(String filepath) throws ResourceException;
}
