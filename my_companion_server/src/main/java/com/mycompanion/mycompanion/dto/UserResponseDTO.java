package com.mycompanion.mycompanion.dto;

import com.mycompanion.mycompanion.entity.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private Long uuid;
    private ResponseType type;
    private String data;
    private String timestamp;

    public UserResponseDTO(Long uuid, ResponseType type, String data, String timestamp) {
        this.uuid = uuid;
        this.type = type;
        this.data = data;
        this.timestamp = timestamp;
    }
}
