package com.esiljak1.familytodo.user;

import com.esiljak1.familytodo.authentication.Authentication;
import com.esiljak1.familytodo.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    private boolean isPasswordValid(String password){
        return password != null && !password.isEmpty();
    }

    private static void userDTOtoUser(User user, UserDTO userDTO){
        user.setName(userDTO.getName() != null ? userDTO.getName() : user.getName());
        user.setSurname(userDTO.getSurname() != null ? userDTO.getSurname() : user.getSurname());
        user.setEmail(userDTO.getEmail() != null ? userDTO.getEmail() : user.getEmail());
    }

    private void updateUserAuth(User user, UserDTO userDTO){
    }

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User postUser(User user){
        if(!isPasswordValid(user.getPassword())){
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        String salt = BCrypt.gensalt();
        user.setPassword(BCrypt.hashpw(user.getPassword(), salt));
        return userRepository.save(user);
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
