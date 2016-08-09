package com.converter.model;

import java.util.List;

public class ConversionRequest {

    private List<String> catCurrency;

    public List<String> getCatCurrency() {
        return catCurrency;
    }

    public void setCatCurrency(List<String> catCurrency) {
        this.catCurrency = catCurrency;
    }
}
