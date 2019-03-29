package com.mycompanion.mycompanion.entity;


import com.mycompanion.mycompanion.dto.LightDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "light")
@Data
public class Light {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private int light;
    private LocalDateTime timestamp;

    public static Light convertFromDto(LightDTO dto, User user){
        Light light = new Light();
        light.setName(dto.getSensorName());
        light.setLight(dto.getLight());
        light.setTimestamp(LocalDateTime.parse(dto.getTimestamp()));
        light.setUser(user);
        return light;
    }

}
