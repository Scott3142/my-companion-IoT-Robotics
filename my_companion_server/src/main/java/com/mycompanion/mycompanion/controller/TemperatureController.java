package com.mycompanion.mycompanion.controller;

import com.mycompanion.mycompanion.dto.TemperatureDTO;
import com.mycompanion.mycompanion.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/temperatures")
public class TemperatureController {

    private TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService tService){
        temperatureService = tService;
    }

    @CrossOrigin
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TemperatureDTO> saveTemperature(@RequestBody TemperatureDTO newTemp){
        TemperatureDTO temp = temperatureService.saveTemperature(newTemp);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(temp, httpHeaders, HttpStatus.CREATED);
    }
}
