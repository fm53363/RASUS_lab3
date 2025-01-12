package com.fer.hr.temperaturemicroservice.service;

import com.fer.hr.temperaturemicroservice.model.Temperature;
import com.fer.hr.temperaturemicroservice.repository.TemperatureRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class TemperatureService {

    private final TemperatureRepository temperatureRepository;


    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public void save(Temperature temperature) {
        temperatureRepository.save(temperature);
    }

    public Optional<Temperature> getCurrentTemperature() {
        long id = getActiveMinutesSinceEpoch() % 100 + 1;
        return temperatureRepository.findById(id);
    }

    public long getActiveMinutesSinceEpoch() {
        return System.currentTimeMillis() / (1_000 * 60);
    }
}
