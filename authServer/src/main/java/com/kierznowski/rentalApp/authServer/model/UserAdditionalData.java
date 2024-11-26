package com.kierznowski.rentalApp.authServer.model;

import lombok.Data;

@Data
public class UserAdditionalData {
    private Long id;
    private String email;
    private String firstName;
    private String secondName;
    private String phone;
    private String userCity;
    private String userStreet;
    private String userZip;
}
