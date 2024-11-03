package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.RegistrationForm;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/register")
public class RegisterController {

    RestTemplate restTemplate;

    public RegisterController() {
        this.restTemplate = new RestTemplate();
    }

    @PostMapping
    public HttpStatus registerUser(@RequestBody RegistrationForm form) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegistrationForm> requestEntity = new HttpEntity<>(form, headers);
        return restTemplate.postForObject("http://localhost:8080/register", requestEntity, HttpStatus.class);
    }
}
