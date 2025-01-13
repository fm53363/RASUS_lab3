package com.fer.hr.aggregatormicroservice.exception;

public class TemperatureNotFoundException extends RuntimeException {
    public TemperatureNotFoundException(String message) {
        super(message);
    }
}
