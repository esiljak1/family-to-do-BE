package com.esiljak1.familytodo.user;

import com.esiljak1.familytodo.interfaces.CRUDInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController implements CRUDInterface<Long, User> {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> post(@RequestBody User user){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("Data", userService.postUser(user));
        return ResponseEntity.ok(responseMap);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> get(){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("Data", userService.getUsers());
        return ResponseEntity.ok(responseMap);
    }

    @Override
    @PutMapping(path = "{userId}")
    public ResponseEntity<?> update(@PathVariable("userId") Long userId, @RequestBody User user){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("Data", userService.updateUser(userId, user));
        return ResponseEntity.ok(responseMap);
    }

    @Override
    @DeleteMapping(path = "{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId") Long userId){
        Map<String, Object> responseMap = new HashMap<>();
        userService.deleteUser(userId);
        responseMap.put("Data", "Success");
        return ResponseEntity.ok(responseMap);
    }
}
