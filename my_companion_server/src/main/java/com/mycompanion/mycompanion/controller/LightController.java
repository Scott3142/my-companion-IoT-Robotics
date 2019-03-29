package com.mycompanion.mycompanion.controller;

import com.mycompanion.mycompanion.dto.LightDTO;
import com.mycompanion.mycompanion.dto.TemperatureDTO;
import com.mycompanion.mycompanion.service.LightService;
import com.mycompanion.mycompanion.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lights")
public class LightController {

    private LightService lightService;

    @Autowired
    public LightController(LightService lService){
        lightService = lService;
    }

    @CrossOrigin
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LightDTO> saveLight(@RequestBody LightDTO newLight){
        LightDTO light = lightService.saveLight(newLight);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(light, httpHeaders, HttpStatus.CREATED);
    }

}
