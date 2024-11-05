package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/account")
public class AccountController {

    RestTemplate restTemplate;

    public AccountController() {
        this.restTemplate = new RestTemplate();
    }


    @PostMapping(path="/{id}", consumes = "application/json")
    ResponseEntity<HttpStatus> editAccountData(@PathVariable("id") Long id, @RequestBody User patch) {
        return restTemplate.postForEntity("http://localhost:8080/account/" + id,
                patch, HttpStatus.class);
    }
}
