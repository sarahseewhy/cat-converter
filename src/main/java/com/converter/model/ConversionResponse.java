package com.converter.model;

import java.math.BigDecimal;
import java.util.List;

public class ConversionResponse {

    private List<String> catCurrency;

    private List<BigDecimal> arabicNumber;

    private BigDecimal result;

    public List<String> getCatCurrency() {
        return catCurrency;
    }

    public void setCatCurrency(List<String> catCurrency) {
        this.catCurrency = catCurrency;
    }

    public List<BigDecimal> getArabicNumber() {
        return arabicNumber;
    }

    public void setArabicNumber(List<BigDecimal> arabicNumber) {
        this.arabicNumber = arabicNumber;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

}
