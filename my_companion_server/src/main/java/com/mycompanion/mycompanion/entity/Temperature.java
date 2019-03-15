package com.mycompanion.mycompanion.entity;

import com.mycompanion.mycompanion.dto.TemperatureDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "temperature")
@Data
public class Temperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private int temperature;
    private int humidity;
    private LocalDateTime timestamp;

    public static Temperature convertFromDto(TemperatureDTO dto, User user){
        Temperature temperature = new Temperature();
        temperature.setName(dto.getSensorName());
        temperature.setTemperature(dto.getTemperature());
        temperature.setHumidity(dto.getHumidity());
        temperature.setTimestamp(LocalDateTime.parse(dto.getTimestamp()));
        temperature.setUser(user);
        return temperature;
    }
}
