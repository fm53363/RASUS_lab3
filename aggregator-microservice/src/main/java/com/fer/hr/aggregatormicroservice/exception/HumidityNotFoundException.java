package com.fer.hr.aggregatormicroservice.exception;

public class HumidityNotFoundException extends RuntimeException {
    public HumidityNotFoundException(String message) {
        super(message);
    }
}
