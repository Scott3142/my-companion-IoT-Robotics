package com.mycompanion.mycompanion.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Temperature {
    private long id;
    private User user;
    private String name;
}
