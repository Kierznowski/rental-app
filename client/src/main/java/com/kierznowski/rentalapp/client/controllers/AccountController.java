package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.UserBasicInfoDTO;
import com.kierznowski.rentalapp.client.model.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Value("basic-user-info")
    String getUserBasicInfoUrl;

    private final RestTemplate restTemplate;

    public AccountController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/user-basic-info")
    public ResponseEntity<UserBasicInfoDTO> getUserName() {
        return restTemplate.getForEntity(getUserBasicInfoUrl, UserBasicInfoDTO.class);
    }




}
