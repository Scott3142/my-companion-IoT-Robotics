package com.mycompanion.mycompanion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureDTO {
    private Long id;
    private Long uuid;
    private String sensorName;
    private int temperature;
    private int humidity;
    private String timestamp;

    public TemperatureDTO(String sensorName, int temperature, int humidity) {
        this.sensorName = sensorName;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public TemperatureDTO(Long uuid, String sensorName, int temperature, int humidity, String timestamp) {
        this.uuid = uuid;
        this.sensorName = sensorName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.timestamp = timestamp;
    }
}
