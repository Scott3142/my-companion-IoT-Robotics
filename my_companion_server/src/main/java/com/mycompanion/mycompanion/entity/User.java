package com.mycompanion.mycompanion.entity;

import com.mycompanion.mycompanion.dto.AccountDTO;
import com.mycompanion.mycompanion.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uuid;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime joinDate;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean enabled;
    @OneToMany(mappedBy = "contactee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;

    public User(String username, String password, String firstName, String lastName, String email, LocalDateTime joinDate, boolean enabled, List<Contact> contacts) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joinDate = joinDate;
        this.enabled = enabled;
        this.contacts = contacts;
    }

    public static User convertFromDto(UserDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setEnabled(true);
        user.setJoinDate(LocalDateTime.now());
        user.setContacts(dto.getContacts()
                .stream()
                .map(contact -> Contact.convertFromDto(contact, user))
                .collect(Collectors.toList()));
        return user;
    }

    public static User convertFromDto(AccountDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setEnabled(true);
        user.setContacts(dto.getContacts()
                .stream()
                .map(contact -> Contact.convertFromDto(contact, user))
                .collect(Collectors.toList()));
        return user;
    }
}
