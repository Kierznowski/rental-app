package com.kierznowski.rentalApp.authServer.controllers;

import com.kierznowski.rentalApp.authServer.model.RegistrationForm;
import com.kierznowski.rentalApp.authServer.model.User;
import com.kierznowski.rentalApp.authServer.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;
    PasswordEncoder passwordEncoder;

    @PostMapping
    public void registerUser(@RequestBody RegistrationForm form) {
        User newUser = new User(form.getEmail(), passwordEncoder.encode(form.getPassword()), "ROLE_USER");
        accountService.registerUser(newUser);
        accountService.sendUserData(form, newUser.getId());
    }

}
