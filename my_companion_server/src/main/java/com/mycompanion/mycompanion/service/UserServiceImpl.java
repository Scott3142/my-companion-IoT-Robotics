package com.mycompanion.mycompanion.service;

import com.mycompanion.mycompanion.dto.AccountDTO;
import com.mycompanion.mycompanion.dto.ContactDTO;
import com.mycompanion.mycompanion.dto.UserDTO;
import com.mycompanion.mycompanion.dto.UserResponseDTO;
import com.mycompanion.mycompanion.entity.User;
import com.mycompanion.mycompanion.entity.UserResponse;
import com.mycompanion.mycompanion.repository.UserRepository;
import com.mycompanion.mycompanion.repository.UserResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserResponseRepository userResponseRepository;

    @Autowired
    public UserServiceImpl(UserRepository uRepo, UserResponseRepository uResRepo){
        userRepository = uRepo;
        userResponseRepository = uResRepo;
    }

    @Override
    public UserDTO create(AccountDTO newUser) {
        User fromDto = User.convertFromDto(newUser);
        fromDto.setJoinDate(LocalDateTime.now());
        User user = userRepository.saveAndFlush(fromDto);
        return new UserDTO(user.getUuid(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getContacts().stream().map(contact -> new ContactDTO(contact.getId(), contact.getFirstName(),
                        contact.getLastName(), contact.getEmail(), contact.getPhone())).collect(Collectors.toList()));
    }

    @Override
    public UserDTO findUserWithID(Long uuid) {
        User user = userRepository.findByUuid(uuid);
        return new UserDTO(user.getUuid(), user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getContacts().stream().map(contact -> new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(),
                contact.getEmail(), contact.getPhone())).collect(Collectors.toList()));
    }

    @Override
    public void recordUserResponse(UserResponseDTO receivedResponse) {
        User user = userRepository.findByUuid(receivedResponse.getUuid());
        UserResponse response = userResponseRepository.saveAndFlush(UserResponse.convertFromDto(receivedResponse, user));

    }

    @Override
    public void delete(Long uuid) {

    }
}
