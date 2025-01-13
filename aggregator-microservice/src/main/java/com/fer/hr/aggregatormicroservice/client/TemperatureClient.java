package com.fer.hr.aggregatormicroservice.client;

import com.fer.hr.aggregatormicroservice.dto.TemperatureDTO;

public interface TemperatureClient {
    TemperatureDTO getCurrentTemperature();
}
