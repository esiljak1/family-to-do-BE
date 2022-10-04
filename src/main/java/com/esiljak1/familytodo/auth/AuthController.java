package com.esiljak1.familytodo.auth;

import com.esiljak1.familytodo.security.JwtTokenUtil;
import com.esiljak1.familytodo.user.User;
import com.esiljak1.familytodo.user.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Log logger = LogFactory.getLog(getClass());
    private final AuthenticationManager authenticationManager;
    private final UserService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> requestBody){
        Map<String, Object> responseMap = new HashMap<>();
        if(!requestBody.containsKey("username") || !requestBody.containsKey("password") || requestBody.size() != 2){
            responseMap.put("error", true);
            responseMap.put("message", "Invalid payload");
            return ResponseEntity.status(400).body(responseMap);
        }
        String username = requestBody.get("username"), password = requestBody.get("password");
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username
                    , password));
            if (auth.isAuthenticated()) {
                logger.info("Logged In");
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                String token = jwtTokenUtil.generateToken(userDetails);
                responseMap.put("error", false);
                responseMap.put("message", "Logged In");
                responseMap.put("token", token);
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", true);
                responseMap.put("message", "Invalid Credentials");
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "User is disabled");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            responseMap.put("error", true);
            responseMap.put("message", "Invalid Credentials");
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        Map<String, Object> responseMap = new HashMap<>();
        User databaseUser = userDetailsService.postUser(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        responseMap.put("user", databaseUser);
        responseMap.put("token", token);
        return ResponseEntity.ok(responseMap);
    }
}
