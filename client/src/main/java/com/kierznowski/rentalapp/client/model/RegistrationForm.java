package com.kierznowski.rentalapp.client.model;

import jakarta.annotation.sql.DataSourceDefinitions;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationForm {

    private String email;
    private String password;
    private String firstName;
    private String secondName;
    private String phone;
    private String userCity;
    private String userStreet;
    private String userZip;
}