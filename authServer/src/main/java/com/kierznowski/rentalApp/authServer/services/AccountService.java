package com.kierznowski.rentalApp.authServer.services;

import com.kierznowski.rentalApp.authServer.model.RegistrationForm;
import com.kierznowski.rentalApp.authServer.model.User;
import com.kierznowski.rentalApp.authServer.model.UserAdditionalData;
import com.kierznowski.rentalApp.authServer.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    UserRepository userRepository;
    RestTemplate restTemplate;

    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.restTemplate = new RestTemplate();
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public ResponseEntity<HttpStatus> sendUserData(RegistrationForm form, Long id) {
        UserAdditionalData additionalData = new UserAdditionalData();
        additionalData.setEmail(form.getEmail());
        additionalData.setFirstName(form.getFirstName());
        additionalData.setSecondName(form.getSecondName());
        additionalData.setPhone(form.getPhone());
        additionalData.setUserCity(form.getUserCity());
        additionalData.setUserStreet(form.getUserStreet());
        additionalData.setUserZip(form.getUserZip());
        additionalData.setId(id);
        
        return restTemplate.postForEntity("localhost:8080/register", additionalData, HttpStatus.class);
    }
}
