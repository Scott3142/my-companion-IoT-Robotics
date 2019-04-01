package com.mycompanion.mycompanion.controller;

import com.mycompanion.mycompanion.dto.DoorDTO;
import com.mycompanion.mycompanion.service.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/doors")
public class DoorController {
    private DoorService doorService;

    @Autowired
    public DoorController(DoorService dService){
        doorService = dService;
    }

    @CrossOrigin
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DoorDTO> saveDoor(@RequestBody DoorDTO newDoor){
        DoorDTO door = doorService.saveDoor(newDoor);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(door, httpHeaders, HttpStatus.CREATED);
    }


}
