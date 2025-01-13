package com.fer.hr.aggregatormicroservice.service;

import com.fer.hr.aggregatormicroservice.client.HumidityClient;
import com.fer.hr.aggregatormicroservice.client.TemperatureClient;
import com.fer.hr.aggregatormicroservice.dto.MeasurementDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AggregatorService {

    private final TemperatureClient temperatureClient;
    private final HumidityClient humidityClient;


    @Value("${humidity.service.url}")
    private String humidityServiceUrl;


    public AggregatorService(TemperatureClient temperatureClient, HumidityClient humidityClient) {
        this.temperatureClient = temperatureClient;
        this.humidityClient = humidityClient;
    }


    public List<MeasurementDTO> getAggregatedMeasurement() {
        var temperatureDTO = temperatureClient.getCurrentTemperature();
        var humidityDTO = humidityClient.getCurrentHumidity();

        List<MeasurementDTO> measurementDTOList = new ArrayList<>();
        measurementDTOList.add(temperatureDTO);
        measurementDTOList.add(humidityDTO);
        return measurementDTOList;

    }








}
