package com.kierznowski.rentalapp.client.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/auth")
public class AuthController {

    @GetMapping("/is-auth")
    public ResponseEntity<Void> checkAuth() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal != "anonymousUser") {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody String email, @RequestBody String password) {
        System.out.println("User " + email + " " + password + " try to logged in");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
