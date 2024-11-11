package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/account")
public class UserController {

    RestTemplate restTemplate;

    public UserController() {
        this.restTemplate = new RestTemplate();
    }


    @PatchMapping(path="/{id}", consumes = "application/json")
    ResponseEntity<HttpStatus> editAccountData(@PathVariable("id") Long id, @RequestBody UserDTO patch) {
        return restTemplate.postForEntity("http://localhost:8080/account/" + id,
                patch, HttpStatus.class);
    }

}
