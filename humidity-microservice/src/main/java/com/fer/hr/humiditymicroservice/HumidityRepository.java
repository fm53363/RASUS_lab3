package com.fer.hr.humiditymicroservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HumidityRepository extends JpaRepository<Humidity,Long> {

    Optional<Humidity> findById(Long id);


}
