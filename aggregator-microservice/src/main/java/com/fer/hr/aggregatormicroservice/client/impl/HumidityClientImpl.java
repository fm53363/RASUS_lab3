package com.fer.hr.aggregatormicroservice.client.impl;

import com.fer.hr.aggregatormicroservice.client.HumidityClient;
import com.fer.hr.aggregatormicroservice.dto.HumidityDTO;
import com.fer.hr.aggregatormicroservice.exception.HumidityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class HumidityClientImpl implements HumidityClient {

    private static final Logger logger = LoggerFactory.getLogger(HumidityClientImpl.class); // Logger initialization


    private final RestClient restClient;

    @Value("${humidity.service.url}")
    private String url;

    public HumidityClientImpl() {
        this.restClient = RestClient.builder().build();
    }

    @Override
    public HumidityDTO getCurrentHumidity() {
        String tmp= url + "/humidity/current";
        logger.info("Get current humidity to server: {}", tmp);

        try {
            return this.restClient.
                    get().
                    uri(tmp).
                    retrieve().
                    body(HumidityDTO.class);
        }catch (Exception e) {
            throw new HumidityNotFoundException("Failed to fetch current humidity");
        }
    }
}
