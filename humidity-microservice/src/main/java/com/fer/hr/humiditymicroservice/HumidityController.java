package com.fer.hr.humiditymicroservice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/humidity")
public class HumidityController {

    private final HumidityService humidityService;

    public HumidityController(HumidityService humidityService) {
        this.humidityService = humidityService;
    }

    @GetMapping("/current")
    public ResponseEntity<Humidity> getTemperature() {
        var temperatureOpt = humidityService.getCurrentHumidity();

        return temperatureOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
