package com.example.restapi.librarymanagement.controller;

import com.example.restapi.librarymanagement.exception.exceptions.IssueCardNotFoundException;
import com.example.restapi.librarymanagement.exception.exceptions.UserNotFoundException;
import com.example.restapi.librarymanagement.model.User;
import com.example.restapi.librarymanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> AllUsers() {
        LOGGER.info("Retriving all users");
        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /** Retrieving user with userid,
     * @throws  UserNotFoundException*/
    @GetMapping(value = "/v1/users/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> userById(@PathVariable Long userid) {
        LOGGER.info("Retriving user with userid "+userid);
        Optional<User> optUser = userService.getUserById(userid);
        if (optUser.isPresent()) {
            return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
        } else throw new UserNotFoundException(userid);

    }


    @PostMapping(value = "/v1/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid User user) {
        LOGGER.info("Creating new user");
        user.setDateOfJoining(LocalDate.now());
        return userService.saveUser(user);
    }


    @PutMapping(value = "v1/users/{userid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long userid) {

        LOGGER.info("Updating user information userid: "+ userid);
        User existUser = userService.getUserById(userid).orElseThrow(() -> new UserNotFoundException(userid));

        existUser.setFullName(user.getFullName());
        existUser.setDateOfJoining(user.getDateOfJoining());

        userService.saveUser(existUser);
        return new ResponseEntity<>(existUser, HttpStatus.OK);

    }

    @DeleteMapping(value = "/v1/users/{userid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userid) {
        LOGGER.info("Deleting a user userid: "+ userid);
        User user = userService.getUserById(userid).orElseThrow(() -> new UserNotFoundException(userid));
        userService.deleteUser(userid);
    }

}
