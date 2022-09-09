package com.esiljak1.familytodo.user;

import com.esiljak1.familytodo.authentication.Authentication;
import com.esiljak1.familytodo.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User postUser(UserDTO userDTO){
        Authentication auth = authenticationService.createPasswordHash(userDTO.getPassword());
        return userRepository.save(new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), auth));
    }

    public void deleteUser(Long userId){
        if(userId == null){
            throw new IllegalArgumentException("You have to pass userId");
        }

        if(!userRepository.existsById(userId))
            return;

        userRepository.deleteById(userId);
    }
}
