package com.kierznowski.rentalapp.client.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bff/login")
public class LoginController {

    RestTemplate restTemplate;

    @PostMapping
    public void login(UserDetails userDetails) {
        restTemplate.postForObject("http://localhost:9000", userDetails, UserDetails.class);
    }
}
