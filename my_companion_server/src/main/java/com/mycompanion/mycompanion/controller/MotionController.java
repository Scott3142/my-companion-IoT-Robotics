package com.mycompanion.mycompanion.controller;

import com.mycompanion.mycompanion.dto.MotionDTO;
import com.mycompanion.mycompanion.service.MotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motion")
public class MotionController {
    private MotionService motionService;

    @Autowired
    public MotionController(MotionService mService){
        motionService = mService;
    }

    @CrossOrigin
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MotionDTO> saveMotion(@RequestBody MotionDTO newMotion){
        MotionDTO motion = motionService.saveMotion(newMotion);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(motion, httpHeaders, HttpStatus.CREATED);
    }
}
