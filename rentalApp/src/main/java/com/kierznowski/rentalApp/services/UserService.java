package com.kierznowski.rentalApp.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    RestTemplate restTemplate;
    PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        restTemplate = new RestTemplate();
        this.passwordEncoder = passwordEncoder;
    }
}
