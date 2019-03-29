package com.mycompanion.mycompanion.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotionDTO {
    private Long id;
    private Long uuid;
    private String sensorName;
    private Boolean motion;
    private String timestamp;

    public MotionDTO(String sensorName, Boolean motion) {
        this.sensorName = sensorName;
        this.motion = motion;
    }

    public MotionDTO(Long uuid, String sensorName, Boolean motion, String timestamp) {
        this.uuid = uuid;
        this.sensorName = sensorName;
        this.motion = motion;
        this.timestamp = timestamp;
    }



}
