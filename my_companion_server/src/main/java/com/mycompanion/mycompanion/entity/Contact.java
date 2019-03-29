package com.mycompanion.mycompanion.entity;

import com.mycompanion.mycompanion.dto.ContactDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contact")
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User contactee;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean enabled;

    public static Contact convertFromDto(ContactDTO dto, User parent){
        Contact contact = new Contact();
        contact.setContactee(parent);
        contact.setEmail(dto.getEmail());
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setPhone(dto.getPhone());
        contact.setEnabled(true);
        return contact;
    }
}
