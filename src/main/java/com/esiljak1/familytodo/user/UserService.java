package com.esiljak1.familytodo.user;

import com.esiljak1.familytodo.authentication.Authentication;
import com.esiljak1.familytodo.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    private static void userDTOtoUser(User user, UserDTO userDTO){
        user.setName(userDTO.getName() != null ? userDTO.getName() : user.getName());
        user.setSurname(userDTO.getSurname() != null ? userDTO.getSurname() : user.getSurname());
        user.setEmail(userDTO.getEmail() != null ? userDTO.getEmail() : user.getEmail());
    }

    private void updateUserAuth(User user, UserDTO userDTO){
        Authentication auth = authenticationService.updatePasswordHash(user.getAuthentication().getId(), userDTO.getPassword());
        if(auth != null){
            user.setAuthentication(auth);
        }
    }

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

    public User updateUser(Long userId, UserDTO userDTO){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new IllegalArgumentException("No user with specified id");
        }

        updateUserAuth(user.get(), userDTO);
        userDTOtoUser(user.get(), userDTO);

        return userRepository.save(user.get());
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
