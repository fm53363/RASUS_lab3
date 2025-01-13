package com.fer.hr.aggregatormicroservice.client.impl;

import com.fer.hr.aggregatormicroservice.client.TemperatureClient;
import com.fer.hr.aggregatormicroservice.dto.TemperatureDTO;
import com.fer.hr.aggregatormicroservice.exception.TemperatureNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class TemperatureClientImpl implements TemperatureClient {

    private final RestClient restClient;

    @Value("${temperature.service.url}")
    private String url;

    public TemperatureClientImpl() {
        this.restClient = RestClient.builder().build();
    }

    @Override
    public TemperatureDTO getCurrentTemperature() {
        String tmp =  url + "/temperature/current";
        System.err.println( );
        try{
            return this.restClient.
                    get().
                    uri(url + "/temperature/current").
                    retrieve().
                    body(TemperatureDTO.class);

        } catch (Exception e) {
            throw new TemperatureNotFoundException("Failed to fetch  current temperature");
        }

    }
}
