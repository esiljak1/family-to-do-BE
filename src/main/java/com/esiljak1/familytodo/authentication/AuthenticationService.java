package com.esiljak1.familytodo.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    private boolean isPasswordValid(String password){
        return password != null && !password.isEmpty();
    }

    private Authentication saveAuthToDb(Authentication auth){
        return authenticationRepository.save(auth);
    }

    @Autowired
    public AuthenticationService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    public Authentication createPasswordHash(String password){
        if(!isPasswordValid(password)){
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        return saveAuthToDb(new Authentication(hash));
    }

    public Authentication updatePasswordHash(Long authId, String password){
        if(!isPasswordValid(password)){
            return null;
        }
        Optional<Authentication> auth = authenticationRepository.findById(authId);
        if(auth.isEmpty()){
            return null;
        }
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        auth.get().setHashedPassword(hash);
        return saveAuthToDb(auth.get());
    }
}
