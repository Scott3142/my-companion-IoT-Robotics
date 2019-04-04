package com.mycompanion.mycompanion.controller;

import com.mycompanion.mycompanion.dto.AccountDTO;
import com.mycompanion.mycompanion.dto.UserDTO;
import com.mycompanion.mycompanion.entity.User;
import com.mycompanion.mycompanion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService uService){
        userService = uService;
    }

    @RequestMapping(path="/test")
    public ResponseEntity<Object> test(){
        return ResponseEntity.ok("a test string");
    }

    @RequestMapping(path = "/register")
    public void register(@RequestBody AccountDTO user){
        userService.create(user);
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> createUser(@RequestBody AccountDTO newUser){
        UserDTO response = userService.create(newUser);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{uuid}", produces = "application/json")
    public ResponseEntity<UserDTO> get(@PathVariable Long uuid){
        System.out.println("Finding user with uuid: " + uuid);
        return ResponseEntity.ok(userService.findUserWithID(uuid));
    }

    @RequestMapping(path="/test")
    public ResponseEntity<Object> test(){
        return ResponseEntity.ok("a test string");
    }
}
