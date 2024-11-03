package com.kierznowski.rentalApp.controllers;

import com.kierznowski.rentalApp.models.RegistrationForm;
import com.kierznowski.rentalApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String registrationForm() {
        return "register";
    }

    @PostMapping
    public String registerUser(@RequestBody RegistrationForm form) {
        System.out.println(form);
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
