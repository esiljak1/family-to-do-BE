package com.esiljak1.familytodo.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public List<User> getUsers(){
        return List.of(
                new User(1L, "Student", "Prvi", "student1@gmail.com"),
                new User(2L, "Student", "Drugi", "student2@gmail.com")
        );
    }
}
