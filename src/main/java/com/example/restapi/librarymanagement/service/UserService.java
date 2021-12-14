package com.example.restapi.librarymanagement.service;

import com.example.restapi.librarymanagement.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    Optional<User> getUserById(Long userId);
    User saveUser(User user);
    User updateUser(User user,Long userId);
    void deleteUser(Long userId);

}
