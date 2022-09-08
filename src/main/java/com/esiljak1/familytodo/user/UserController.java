package com.esiljak1.familytodo.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @GetMapping
    public List<User> getUsers(){
        return List.of(
                new User(1L, "Student", "1", "student1@gmail.com"),
                new User(2L, "Student", "2", "student2@gmail.com")
        );
    }
}
