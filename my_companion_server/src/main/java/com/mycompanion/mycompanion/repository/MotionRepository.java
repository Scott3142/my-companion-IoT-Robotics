package com.mycompanion.mycompanion.repository;

import com.mycompanion.mycompanion.entity.Motion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotionRepository extends JpaRepository<Motion, Long> {
}
