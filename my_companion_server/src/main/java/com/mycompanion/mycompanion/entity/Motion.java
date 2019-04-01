package com.mycompanion.mycompanion.entity;

import com.mycompanion.mycompanion.dto.MotionDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "motion")
@Data
public class Motion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private Boolean motion;
    private LocalDateTime timestamp;

    public static Motion convertFromDTO(MotionDTO dto, User user){
        Motion motion = new Motion();
        motion.setName(dto.getSensorName());
        motion.setMotion(dto.getMotion());
        motion.setTimestamp(LocalDateTime.parse(dto.getTimestamp()));
        motion.setUser(user);
        return motion;
    }



}
