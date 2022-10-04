package com.esiljak1.familytodo.security;

import com.esiljak1.familytodo.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;
    private final UserDetailsService userDetailsService;
    private final AuthenticationConfiguration authConfiguration;

    public SecurityConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter, AuthenticationConfiguration authConfiguration) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userDetailsService = userDetailsService;
        this.authConfiguration = authConfiguration;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Autowired
    public void globalConfigure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http.csrf().disable().authorizeRequests().antMatchers("/auth/*").permitAll()
                .anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            Map<String, Object> responseMap = new HashMap<>();
            ObjectMapper mapper = new ObjectMapper();
            response.setStatus(401);
            responseMap.put("error", true);
            responseMap.put("message", "Unauthorized");
            response.setHeader("content-type", "application/json");
            String responseMsg = mapper.writeValueAsString(responseMap);
            response.getWriter().write(responseMsg);
        }).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
