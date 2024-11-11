package com.kierznowski.rentalApp.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name="User_data")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private final String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String userCity;
    private String userStreet;
    private String userZip;
    @OneToMany
    private List<Offer> offerList;

    public User(String email, String password, String firstName, String lastName,
                String phone, String userCity, String userStreet, String userZip) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.userCity = userCity;
        this.userStreet = userStreet;
        this.userZip = userZip;
    }

}
