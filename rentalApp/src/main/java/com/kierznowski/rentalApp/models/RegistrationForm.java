package com.kierznowski.rentalApp.models;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String email;
    private String password;
    private String firstName;
    private String secondName;
    private String phone;
    private String userCity;
    private String userStreet;
    private String userZip;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(email, passwordEncoder.encode(password),
                firstName, secondName, phone, userCity, userStreet, userZip);
    }
}
