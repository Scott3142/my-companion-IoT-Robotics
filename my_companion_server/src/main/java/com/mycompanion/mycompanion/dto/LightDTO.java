package com.mycompanion.mycompanion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LightDTO {
    private Long id;
    private Long uuid;
    private String sensorName;
    private int light;
    private String timestamp;

    public LightDTO(String sensorName, int light) {
        this.sensorName = sensorName;
        this.light = light;
    }

    public LightDTO(Long uuid, String sensorName, int light, String timestamp) {
        this.uuid = uuid;
        this.sensorName = sensorName;
        this.light = light;
        this.timestamp = timestamp;
    }
}
