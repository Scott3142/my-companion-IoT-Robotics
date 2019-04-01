package com.mycompanion.mycompanion.controller;

import com.mycompanion.mycompanion.dto.ActivityDTO;
import com.mycompanion.mycompanion.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService aService){
        activityService = aService;
    }

    @CrossOrigin
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ActivityDTO> saveActivity(@RequestBody ActivityDTO newActivity){
        ActivityDTO activity = activityService.saveActivity(newActivity);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(activity, httpHeaders, HttpStatus.CREATED);
    }
}
