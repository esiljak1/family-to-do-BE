package com.esiljak1.familytodo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private boolean isPasswordValid(String password){
        return password != null && !password.isEmpty();
    }

    private void updateUserFields(User dbUser, User updatedUser){
        dbUser.setName(updatedUser.getName() != null ? updatedUser.getName() : dbUser.getName());
        dbUser.setSurname(updatedUser.getSurname() != null ? updatedUser.getSurname() : dbUser.getSurname());
        dbUser.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : dbUser.getEmail());
        if(isPasswordValid(updatedUser.getPassword()))
            dbUser.setPassword(updatedUser.getPassword());
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User postUser(User user){
        if(!isPasswordValid(user.getPassword())){
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user){
        Optional<User> dbUser = userRepository.findById(userId);
        if(dbUser.isEmpty()){
            throw new IllegalArgumentException("No user with specified id");
        }

        updateUserFields(dbUser.get(), user);

        return userRepository.save(dbUser.get());
    }

    public void deleteUser(Long userId){
        if(userId == null){
            throw new IllegalArgumentException("You have to pass userId");
        }

        if(!userRepository.existsById(userId))
            return;

        userRepository.deleteById(userId);
    }

    public org.springframework.security.core.userdetails.User loadUserByUsername(String username){
        User user = userRepository.findUserByEmail(username);
        List<GrantedAuthority> authorityList = new ArrayList<>(user.getAuthorities());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorityList);
    }
}
