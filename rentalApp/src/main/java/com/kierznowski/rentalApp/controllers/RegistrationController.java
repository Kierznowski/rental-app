package com.kierznowski.rentalApp.controllers;

import com.kierznowski.rentalApp.models.RegistrationForm;
import com.kierznowski.rentalApp.models.User;
import com.kierznowski.rentalApp.repositories.UserRepository;
import com.kierznowski.rentalApp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

    private UserRepository userRepository;
    private UserService userService;

    @PostMapping
    public String saveUserData(@RequestBody RegistrationForm form, Long userId) {
        User newUser = form.toUser();
        newUser.setUserId(userId);
        userRepository.save(newUser);
        return "redirect:/login";
    }

}
