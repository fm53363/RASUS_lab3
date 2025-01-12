package com.fer.hr.temperaturemicroservice.repository;

import com.fer.hr.temperaturemicroservice.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Integer> {

    Optional<Temperature> findById(Long id);

}
