package com.fer.hr.aggregatormicroservice.controller;

import com.fer.hr.aggregatormicroservice.dto.MeasurementDTO;
import com.fer.hr.aggregatormicroservice.service.AggregatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
