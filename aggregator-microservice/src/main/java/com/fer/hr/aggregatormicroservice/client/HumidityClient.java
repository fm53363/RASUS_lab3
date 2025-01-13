package com.fer.hr.aggregatormicroservice.client;

import com.fer.hr.aggregatormicroservice.dto.HumidityDTO;

public interface HumidityClient {
    HumidityDTO getCurrentHumidity();
}
