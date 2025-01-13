package com.kierznowski.rentalApp.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;


@Data
public class RegistrationForm {

    @NotNull
    @Email(message = "invalid email format")
    private String email;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String secondName;

    @NotNull
    private String phone;

    private String userCity;
    private String userStreet;
    private String userZip;

    public User toUser() {
        return new User(email, firstName, secondName, phone, userCity, userStreet, userZip);
    }
}
