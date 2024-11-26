package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.RegistrationForm;
import com.kierznowski.rentalapp.client.services.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/bff/auth")
public class AuthorizationController {

    AuthorizationService authorizationService;

    @GetMapping("/is-auth")
    public ResponseEntity<Void> checkAuth() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal != null && !principal.equals("anonymousUser")) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public HttpStatus registerUser(@RequestBody RegistrationForm form) {
        return authorizationService.registerUser(form);
    }

}
