package com.fer.hr.humiditymicroservice;

import com.opencsv.CSVReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class HumidityMicroserviceApplication implements CommandLineRunner {
    private static final String CSV_FILE_NAME = "readings[6].csv";
    private final HumidityService humidityService;

    public HumidityMicroserviceApplication(HumidityService humidityService) {
        this.humidityService = humidityService;
    }


    public static void main(String[] args) {
        SpringApplication.run(HumidityMicroserviceApplication.class, args);
    }

    public void run(String... args) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(CSV_FILE_NAME));
        List<String[]> records = reader.readAll();
        Iterator<String[]> iterator = records.iterator();

        boolean isFirst = true;
        while (iterator.hasNext()) {
            String[] record = iterator.next();
            if(isFirst) {
                isFirst = false;
                continue;
            }
            Double value = Double.parseDouble(record[2]);

            Humidity humidity = Humidity.builder()
                    .value(value)
                    .unit("%")
                    .build();
            humidityService.save(humidity);
        }
        reader.close();

    }
}
