package com.converter.resources.processor;

import com.converter.exception.ResourceException;
import com.converter.model.ConversionRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConversionProcessorImpl implements ConversionProcessor {

    private static final String QUESTION_MARK = "?";
    private static final String END_OF_LINE = "\\r?\\n";

    private List<ConversionRequest> conversionRequests = new ArrayList<>();

    @Override
    public List<ConversionRequest> processRequests(String filePath) throws ResourceException {

        try {
            File file = retrieveFilePath(filePath);

            List<String> catCurrency = readInput(file.getPath());

            validateCatCurrency(catCurrency);

            generateConversionRequests(catCurrency, conversionRequests);

        } catch (Exception e) {
            throw new ResourceException("Cannot process request: " + e);
        }

        return conversionRequests;
    }

    private File retrieveFilePath(String filePath) throws ResourceException {

        File file;
        try {

            ClassLoader classLoader = getClass().getClassLoader();
            file = new File(classLoader.getResource(filePath).getFile());

        } catch (Exception e) {
            throw new ResourceException("Cannot process invalid file: " + filePath);
        }

        return file;
    }

    private List<String> readInput(String filePath) throws ResourceException {

        List<String> fileInput;

        try {
            fileInput = Files.lines(Paths.get(filePath))
                            .map(line -> line.split(END_OF_LINE))
                            .flatMap(Arrays::stream)
                            .filter(this::isAQuestion)
                            .collect(Collectors.toList());
        } catch (IOException e) {
            throw new ResourceException("Error reading file: " + filePath);
        }

        return fileInput;
    }

    private void generateConversionRequests(List<String> fileInput, List<ConversionRequest> conversionRequests) {
        fileInput.stream().forEach(line -> {
            List<String> catCurrency = new ArrayList<>();
            catCurrency.add(line);
            ConversionRequest conversionRequest = new ConversionRequest();
            conversionRequest.setCatCurrency(catCurrency);

            conversionRequests.add(conversionRequest);
        });
    }

    private boolean isAQuestion(String line) {
        return line.contains(QUESTION_MARK);
    }

    private void validateCatCurrency(List<String> catCurrency) throws ResourceException {
        if (0 == catCurrency.size()) {
            throw new ResourceException("Cannot process invalid request: " + catCurrency);
        }
    }
}
