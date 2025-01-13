package com.fer.hr.humiditymicroservice;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HumidityService {

    private final HumidityRepository humidityRepository;

    public HumidityService(HumidityRepository humidityRepository) {
        this.humidityRepository = humidityRepository;
    }


    public void save(Humidity humidity) {
        humidityRepository.save(humidity);
    }

    public Optional<Humidity> getCurrentHumidity() {
        long id = getActiveMinutesSinceEpoch() % 100 + 1;
        return humidityRepository.findById(id);
    }

    public long getActiveMinutesSinceEpoch() {
        return System.currentTimeMillis() / (1_000 * 60);
    }

}
