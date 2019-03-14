package com.mycompanion.mycompanion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<ContactDTO> contacts;
}
