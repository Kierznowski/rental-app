package com.kierznowski.rentalapp.client.services;

import com.kierznowski.rentalapp.client.model.RegistrationForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationService {

    RestTemplate restTemplate;

    @Value("${authorization-server.url.register-user}")
    String registerUserUrl;

    public AuthorizationService() {
        this.restTemplate = new RestTemplate();
    }

    public HttpStatus registerUser(RegistrationForm form) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegistrationForm> requestEntity = new HttpEntity<>(form, headers);

        return restTemplate.postForObject(registerUserUrl, requestEntity, HttpStatus.class);
    }
}
