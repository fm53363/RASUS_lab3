package com.fer.hr.temperaturemicroservice.controller;

import com.fer.hr.temperaturemicroservice.model.Temperature;
import com.fer.hr.temperaturemicroservice.service.TemperatureService;
import jakarta.persistence.GeneratedValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/current")
    public ResponseEntity<Temperature> getTemperature() {
        var temperatureOpt = temperatureService.getCurrentTemperature();

        return temperatureOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
