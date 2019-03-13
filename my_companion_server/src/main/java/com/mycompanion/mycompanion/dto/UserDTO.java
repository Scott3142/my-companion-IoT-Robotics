package com.mycompanion.mycompanion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long uuid;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<ContactDTO> contacts;
}
