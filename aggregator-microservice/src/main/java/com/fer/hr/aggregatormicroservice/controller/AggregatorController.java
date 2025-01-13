package com.fer.hr.aggregatormicroservice.controller;

import com.fer.hr.aggregatormicroservice.dto.MeasurementDTO;
import com.fer.hr.aggregatormicroservice.service.AggregatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/measurements")
public class AggregatorController {

    private final AggregatorService aggregatorService;

    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @GetMapping("/aggregate")
    public List<MeasurementDTO> getAggregate() {
        return aggregatorService.getAggregatedMeasurement();
    }

    @GetMapping("/temperature/unit")
    public String getTemperatureUnit() {
        return aggregatorService.getTemperatureUnit();
    }

    @PostMapping("/temperature/unit/convert")
    public ResponseEntity<String> setTemperatureUnit(@RequestBody String unit) {
        Optional<String> opt =  aggregatorService.convertTemperatureUnit(unit);

        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.badRequest().body("Available units are: K and C");


    }



}
