package com.fer.hr.humiditymicroservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/humidity")
public class HumidityController {

    private static final Logger logger = LoggerFactory.getLogger(HumidityController.class); // Logger initialization

    private final HumidityService humidityService;

    public HumidityController(HumidityService humidityService) {
        this.humidityService = humidityService;
    }

    @GetMapping("/current")
    public ResponseEntity<Humidity> getTemperature() {
        logger.info("Fetching current humidity data");


        var humidityOpt = humidityService.getCurrentHumidity();
        if (humidityOpt.isPresent()) {

            logger.info("Current humidity " +humidityOpt.get()+ " retrieved successfully");
            return ResponseEntity.ok(humidityOpt.get());
        } else {
            logger.warn("Current humidity not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
