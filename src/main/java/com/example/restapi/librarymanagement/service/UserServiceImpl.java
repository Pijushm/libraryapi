package com.example.restapi.librarymanagement.service;

import com.example.restapi.librarymanagement.model.User;
import com.example.restapi.librarymanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @Override
    public List<User> getAllUsers() {

        LOGGER.info(" Retrieving all users");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        LOGGER.info(" Retrieving user with userid "+ userId);
        return userRepository.findById(userId);
    }

    @Override
    public User saveUser(User user) {

        LOGGER.info(" Persisting new user ");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Long userId) {
        LOGGER.info(" Updating user in database userid "+userId);
           user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        LOGGER.info(" Delete user from database userid "+userId);
        userRepository.deleteById(userId);
    }
}
