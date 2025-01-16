package com.fer.hr.aggregatormicroservice.client.impl;

import com.fer.hr.aggregatormicroservice.client.TemperatureClient;
import com.fer.hr.aggregatormicroservice.dto.TemperatureDTO;
import com.fer.hr.aggregatormicroservice.exception.TemperatureNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class TemperatureClientImpl implements TemperatureClient {
    private static final Logger logger = LoggerFactory.getLogger(TemperatureClientImpl.class); // Logger initialization


    private final RestClient restClient;

    @Value("${temperature.service.url}")
    private String url;

    public TemperatureClientImpl() {
        this.restClient = RestClient.builder().build();
    }

    @Override
    public TemperatureDTO getCurrentTemperature() {
        String tmp =  url + "/temperature/current";

        logger.info("Get current temperature to server: {}", tmp);
        System.err.println( );
        try{
            return this.restClient.
                    get().
                    uri(tmp).
                    retrieve().
                    body(TemperatureDTO.class);

        } catch (Exception e) {
            throw new TemperatureNotFoundException("Failed to fetch  current temperature");
        }

    }
}
