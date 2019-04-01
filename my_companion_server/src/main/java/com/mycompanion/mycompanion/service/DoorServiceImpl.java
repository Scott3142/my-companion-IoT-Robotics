package com.mycompanion.mycompanion.service;

import com.mycompanion.mycompanion.dto.DoorDTO;
import com.mycompanion.mycompanion.entity.Door;
import com.mycompanion.mycompanion.entity.User;
import com.mycompanion.mycompanion.repository.DoorRepository;
import com.mycompanion.mycompanion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class DoorServiceImpl implements DoorService {
    private DoorRepository doorRepository;
    private UserRepository userRepository;

    @Autowired
    public DoorServiceImpl(DoorRepository dRepo, UserRepository uRepo){
        doorRepository = dRepo;
        userRepository = uRepo;
    }

    @Override
    public DoorDTO saveDoor(DoorDTO newDoor) {
        User user = userRepository.findByUuid(newDoor.getUuid());
        Door door = doorRepository.saveAndFlush(Door.convertFromDTO(newDoor, user));
        return new DoorDTO(door.getId(), door.getUser().getUuid(), door.getName(),
                door.getDoor(), door.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));
    }

}
