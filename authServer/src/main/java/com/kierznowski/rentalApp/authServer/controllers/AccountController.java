package com.kierznowski.rentalApp.authServer.controllers;

import com.kierznowski.rentalApp.authServer.model.RegistrationForm;
import com.kierznowski.rentalApp.authServer.model.User;
import com.kierznowski.rentalApp.authServer.model.UserAdditionalData;
import com.kierznowski.rentalApp.authServer.repositories.UserRepository;
import com.kierznowski.rentalApp.authServer.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;
    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    @PostMapping
    public void registerUser(@RequestBody RegistrationForm form) {
        User newUser = new User(form.getEmail(), passwordEncoder.encode(form.getPassword()), "ROLE_USER");
        accountService.registerUser(newUser);
        accountService.sendUserData(form, newUser.getId());
    }

    @GetMapping("/user-basic-info")
    public ResponseEntity<UserAdditionalData> getUserAdditionalData(Authentication authentication) {
        String email = authentication.getName();
        UserAdditionalData user = userRepository.findUserAdditionalDataByEmail(email);

        UserAdditionalData userAdditionalData = new UserAdditionalData();
        userAdditionalData.setId(user.getId());
        userAdditionalData.setEmail(user.getEmail());
        userAdditionalData.setFirstName(user.getFirstName());
        userAdditionalData.setSecondName(user.getSecondName());
        userAdditionalData.setPhone(user.getPhone());
        userAdditionalData.setUserCity(user.getUserCity());
        userAdditionalData.setUserStreet(user.getUserStreet());
        userAdditionalData.setUserZip(user.getUserZip());

        return ResponseEntity.ok(userAdditionalData);
    }


}
