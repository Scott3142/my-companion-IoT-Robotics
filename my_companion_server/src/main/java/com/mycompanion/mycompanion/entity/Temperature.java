package com.mycompanion.mycompanion.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
@Data
public class Temperature {
    private Long id;
    private User user;
    private String name;
}
