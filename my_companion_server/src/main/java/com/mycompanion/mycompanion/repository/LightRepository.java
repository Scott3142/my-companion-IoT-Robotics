package com.mycompanion.mycompanion.repository;

import com.mycompanion.mycompanion.entity.Light;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightRepository extends JpaRepository<Light, Long> {
}
