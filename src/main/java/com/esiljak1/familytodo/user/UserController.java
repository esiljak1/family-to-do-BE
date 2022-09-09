package com.esiljak1.familytodo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User postUser(@RequestBody UserDTO userDTO){
        return userService.postUser(userDTO);
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PutMapping(path = "{userId}")
    public User updateUser(@PathVariable("userId") Long userId, @RequestBody UserDTO userDTO){
        return userService.updateUser(userId, userDTO);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }
}
