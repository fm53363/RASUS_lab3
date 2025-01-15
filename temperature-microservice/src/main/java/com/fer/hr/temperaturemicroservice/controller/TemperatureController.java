package com.fer.hr.temperaturemicroservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fer.hr.temperaturemicroservice.model.Temperature;
import com.fer.hr.temperaturemicroservice.service.TemperatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    private static final Logger logger = LoggerFactory.getLogger(TemperatureController.class); // Logger initialization

    private final TemperatureService temperatureService;



    public TemperatureController(TemperatureService temperatureService, ObjectMapper objectMapper) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/current")
    public ResponseEntity<Temperature> getTemperature() throws JsonProcessingException {
        logger.info("Fetching current temperature data");

        var temperatureOpt = temperatureService.getCurrentTemperature();
        if (temperatureOpt.isPresent()) {

            logger.info("Current temperature " +temperatureOpt.get()+ " retrieved successfully");
            return ResponseEntity.ok(temperatureOpt.get());
        } else {
            logger.warn("Current temperature not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
