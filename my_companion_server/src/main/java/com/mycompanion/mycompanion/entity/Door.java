package com.mycompanion.mycompanion.entity;

import com.mycompanion.mycompanion.dto.DoorDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "door")
@Data
public class Door {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private Boolean Door;
    private LocalDateTime timestamp;

    public static Door convertFromDTO(DoorDTO dto, User user){
        Door door = new Door();
        door.setName(dto.getSensorName());
        door.setDoor(dto.getDoor());
        door.setTimestamp(LocalDateTime.parse(dto.getTimestamp()));
        door.setUser(user);
        return door;
    }

}
