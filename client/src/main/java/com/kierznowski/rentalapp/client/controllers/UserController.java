package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/account")
public class UserController {

    @Value("${resource.url.user.edit-data}")
    String editUrl;

    RestTemplate restTemplate;

    public UserController() {
        this.restTemplate = new RestTemplate();
    }


    @PatchMapping(path="/{id}", consumes = "application/json")
    ResponseEntity<HttpStatus> editAccountData(@PathVariable("id") Long id, @RequestBody UserDTO patch) {
        return restTemplate.postForEntity(editUrl + id,
                patch, HttpStatus.class);
    }

}
