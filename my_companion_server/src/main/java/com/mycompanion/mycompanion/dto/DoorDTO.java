package com.mycompanion.mycompanion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoorDTO {
    private Long id;
    private Long uuid;
    private String sensorName;
    private Boolean door;
    private String timestamp;

    public DoorDTO(String sensorName, Boolean door) {
        this.sensorName = sensorName;
        this.door = door;
    }

    public DoorDTO(Long uuid, String sensorName, Boolean door, String timestamp) {
        this.uuid = uuid;
        this.sensorName = sensorName;
        this.door = door;
        this.timestamp = timestamp;
    }


}
