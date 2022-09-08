package com.esiljak1.familytodo.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    @Autowired
    public AuthenticationService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    public Authentication createPasswordHash(String password){
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        return authenticationRepository.save(new Authentication(hash));
    }
}
