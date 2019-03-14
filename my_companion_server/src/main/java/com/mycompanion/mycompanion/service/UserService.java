package com.mycompanion.mycompanion.service;

import com.mycompanion.mycompanion.dto.AccountDTO;
import com.mycompanion.mycompanion.dto.UserDTO;
import com.mycompanion.mycompanion.entity.User;

public interface UserService {
    UserDTO create(AccountDTO newUser);
    UserDTO findUserWithID(Long uuid);
    void delete(Long uuid);
}
