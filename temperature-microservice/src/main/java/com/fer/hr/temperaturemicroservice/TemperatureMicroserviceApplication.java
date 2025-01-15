package com.fer.hr.temperaturemicroservice;

import com.fer.hr.temperaturemicroservice.model.Temperature;
import com.fer.hr.temperaturemicroservice.service.TemperatureService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class TemperatureMicroserviceApplication implements CommandLineRunner {

    private static final String CSV_FILE_NAME = "classpath:readings[6].csv"; // Putanja do fajla u resources folderu


    @Autowired
    private TemperatureService temperatureService;


    public static void main(String[] args) {

        SpringApplication.run(TemperatureMicroserviceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        File file = ResourceUtils.getFile(CSV_FILE_NAME);
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> records = reader.readAll();
            Iterator<String[]> iterator = records.iterator();

            boolean isFirst = true;
            while (iterator.hasNext()) {
                String[] record = iterator.next();
                if(isFirst) {
                    isFirst = false;
                    continue;
                }
                Double value = Double.parseDouble(record[0]);

                Temperature temp = Temperature.builder()
                        .value(value)
                        .unit("C")
                        .build();
                temperatureService.save(temp);
            }

        }catch (Exception e){
            e.printStackTrace();

        }



    }

}
