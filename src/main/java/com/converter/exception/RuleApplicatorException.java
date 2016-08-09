package com.converter.exception;

public class RuleApplicatorException extends Exception {

    public RuleApplicatorException(String s, Exception e) {}

    public RuleApplicatorException(String message) {
        super(message);
    }
}
