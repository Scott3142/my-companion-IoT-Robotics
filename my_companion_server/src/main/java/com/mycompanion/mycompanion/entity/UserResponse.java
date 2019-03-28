package com.mycompanion.mycompanion.entity;

import com.mycompanion.mycompanion.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_response")
public class UserResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('action', 'speech')")
    private ResponseType type;
    private String data;
    private String context;
    private LocalDateTime timestamp;

    public static UserResponse convertFromDto(UserResponseDTO dto, User user){
        UserResponse response = new UserResponse();
        response.setUser(user);
        response.setType(dto.getType());
        response.setData(dto.getData());
        response.setTimestamp(LocalDateTime.parse(dto.getTimestamp()));
        return response;
    }
}
