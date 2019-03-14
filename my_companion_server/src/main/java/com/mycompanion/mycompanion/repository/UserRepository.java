package com.mycompanion.mycompanion.repository;

import com.mycompanion.mycompanion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(Long uuid);
}
