package com.mycompanion.mycompanion.repository;

import com.mycompanion.mycompanion.entity.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorRepository extends JpaRepository<Door, Long> {
}
