package com.mycompanion.mycompanion.repository;

import com.mycompanion.mycompanion.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
}
