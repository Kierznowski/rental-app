package com.kierznowski.rentalApp.controllers;

import com.kierznowski.rentalApp.models.PasswordChangeRequest;
import com.kierznowski.rentalApp.models.User;
import com.kierznowski.rentalApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @PatchMapping("/{userId}")
    public ResponseEntity<HttpStatus> patchUser(@PathVariable Long userId, @RequestBody User patch) {
        User user = userRepository.findById(userId).get();

        if(!patch.getFirstName().equals("")) {
            user.setFirstName(patch.getFirstName());
        }
        if(!patch.getLastName().equals("")) {
            user.setLastName(patch.getLastName());
        }
        if(!patch.getPhone().equals("")) {
            user.setPhone(patch.getPhone());
        }
        if(!patch.getUserCity().equals("")) {
            user.setUserCity(patch.getUserCity());
        }
        if(!patch.getUserStreet().equals("")) {
            user.setUserStreet(patch.getUserStreet());
        }
        if(!patch.getUserZip().equals("")) {
            user.setUserZip(patch.getUserZip());
        }
        userRepository.save(user);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @PatchMapping("/password/{userId}")
    public ResponseEntity<HttpStatus> changePassword(@PathVariable Long userId, @RequestBody PasswordChangeRequest request) {
        User user = userRepository.findById(userId).get();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }
}
