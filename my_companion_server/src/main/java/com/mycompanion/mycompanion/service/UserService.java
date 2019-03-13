package com.mycompanion.mycompanion.service;

import com.mycompanion.mycompanion.entity.User;

public interface UserService {
    User create(User user);
    User findUser(long uuid);
    void delete(long uuid);
}
