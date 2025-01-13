package com.fer.hr.aggregatormicroservice.configuration;

import com.fer.hr.aggregatormicroservice.exception.HumidityNotFoundException;
import com.fer.hr.aggregatormicroservice.exception.TemperatureNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class ExceptionHandlerController {



    @ExceptionHandler(TemperatureNotFoundException.class)
    public final ResponseEntity<String> handleTemperatureNotFoundException(TemperatureNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(HumidityNotFoundException.class)
    public final ResponseEntity<String> handleHumidityNotFoundException(HumidityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
