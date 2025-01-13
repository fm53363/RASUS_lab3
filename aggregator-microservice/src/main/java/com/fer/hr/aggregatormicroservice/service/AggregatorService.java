package com.fer.hr.aggregatormicroservice.service;

import com.fer.hr.aggregatormicroservice.client.HumidityClient;
import com.fer.hr.aggregatormicroservice.client.TemperatureClient;
import com.fer.hr.aggregatormicroservice.dto.MeasurementDTO;
import com.fer.hr.aggregatormicroservice.dto.TemperatureDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AggregatorService {

    private final TemperatureClient temperatureClient;
    private final HumidityClient humidityClient;


    @Getter
    @Value("${temperature.unit}")
    private String temperatureUnit;


    public AggregatorService(TemperatureClient temperatureClient, HumidityClient humidityClient) {
        this.temperatureClient = temperatureClient;
        this.humidityClient = humidityClient;
    }




    private void convertTemperature(TemperatureDTO temperatureDTO) {
        if(!temperatureDTO.getUnit().equals(temperatureUnit)) {
            if(temperatureUnit.equals("K")) {
                temperatureDTO.setUnit("K");
                temperatureDTO.setValue(temperatureDTO.getValue() + 273.15);
            }else if(temperatureUnit.equals("C")) {
                temperatureDTO.setUnit("C");
                temperatureDTO.setValue(temperatureDTO.getValue() - 273.15);
            }
        }
    }

    public List<MeasurementDTO> getAggregatedMeasurement() {
        var temperatureDTO = temperatureClient.getCurrentTemperature();
        convertTemperature(temperatureDTO);
        var humidityDTO = humidityClient.getCurrentHumidity();

        List<MeasurementDTO> measurementDTOList = new ArrayList<>();
        measurementDTOList.add(temperatureDTO);
        measurementDTOList.add(humidityDTO);
        return measurementDTOList;

    }

    public Optional<String> convertTemperatureUnit(String temperatureUnit) {

        if(temperatureUnit.equals("C") || temperatureUnit.equals("K")) {
            this.temperatureUnit = temperatureUnit.equals("C") ? "C" : "K";
            return   Optional.of("Temperature unit set to " + this.temperatureUnit);
        }

        return Optional.empty();



    }
}
