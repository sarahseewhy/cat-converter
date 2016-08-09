package com.converter.services.applicator;

import com.converter.exception.InvalidCatCurrencyException;
import com.converter.exception.ServiceException;
import com.converter.model.ConversionRequest;

import java.util.List;

import static com.converter.util.CatConstants.SPACE;
import static com.converter.util.CatCurrency.*;
import static java.util.Collections.frequency;

public class RulesApplicatorImpl implements RulesApplicator {

    private static final int HIGHEST_POSSIBLE_FREQUENCY = 3;
    private static final int LOWEST_POSSIBLE_FREQUENCY = 1;

    @Override
    public ConversionRequest applyCurrencyRules(ConversionRequest conversionRequest) throws ServiceException {

        final List<String> catCurrency = conversionRequest.getCatCurrency();

        try {

            applyLowDenominationReducerRules(catCurrency);

            applyHighDenominationReducerRules(catCurrency);

            if (isInvalidCatCurrency(catCurrency)) {
                throw new InvalidCatCurrencyException("Invalid cat currency: " + catCurrency);
            }

        } catch (Exception e) {
            throw new ServiceException("Cannot apply currency rules to request: " + e);
        }

        conversionRequest.setCatCurrency(catCurrency);

        return conversionRequest;
    }

    private void applyHighDenominationReducerRules(List<String> catCurrency) {
        if (catCurrency.contains(HISS.value())) {
            applyReducerCombination(catCurrency, HISS.value());
        }
    }

    private void applyLowDenominationReducerRules(List<String> catCurrency) {
        if (catCurrency.contains(MEOW.value())) {
            applyReducerCombination(catCurrency, MEOW.value());
        }
    }

    private List<String> applyReducerCombination(List<String> catCurrency, String reducer) {

        int reducerPosition = catCurrency.indexOf(reducer);
        int nextPosition = reducerPosition + 1;
        final String nextValue = catCurrency.get(nextPosition);

        String concatenatedValues;

        final String reducibleValue = determineReducible(reducer, nextValue);

        if (nextValue.equals(reducibleValue)) {
            concatenatedValues = concatenateValues(catCurrency, reducerPosition, nextPosition);
            catCurrency.set(reducerPosition, concatenatedValues);
            catCurrency.remove(nextPosition);
        }

        return catCurrency;
    }

    private String concatenateValues(List<String> catCurrency, int reducerPosition, int nextPosition) {
        return catCurrency.get(reducerPosition) + SPACE + catCurrency.get(nextPosition);
    }

    private String determineReducible(String reducer, String nextValue) {

        final String targetValue;

        if (isEligibleToBeReduced(reducer, nextValue)) {
            targetValue = nextValue;
        } else {
            targetValue = YOWL.value();
        }

        return targetValue;
    }

    private boolean isEligibleToBeReduced(String reducer, String nextValue) {
        return reducer.equalsIgnoreCase(MEOW.value()) && isReducibleByReducer(nextValue);
    }

    private boolean isReducibleByReducer(String nextItem) {
        return nextItem.equalsIgnoreCase(PURR.value()) || nextItem.equalsIgnoreCase(HISS.value());
    }

    private boolean isInvalidCatCurrency(List<String> catCurrency) {

        for (String word : catCurrency) {
            if (isOverWordLimit(catCurrency, word)) {
                return true;
            }
        }

        return false;
    }

    private boolean isOverWordLimit(List<String> catCurrency, String word) {

        if (word.equalsIgnoreCase(YOWL.value()) || word.equalsIgnoreCase(PURR.value())) {
            return frequency(catCurrency, word) > LOWEST_POSSIBLE_FREQUENCY;
        }

        return frequency(catCurrency, word) > HIGHEST_POSSIBLE_FREQUENCY;
    }

}
