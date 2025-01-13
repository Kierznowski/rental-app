package com.kierznowski.rentalApp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name="User_data")
@Data
@EqualsAndHashCode(exclude = "offerList")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private final String email;
    private String firstName;
    private String secondName;
    private String phone;
    private String userCity;
    private String userStreet;
    private String userZip;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Offer> offerList = new HashSet<>();

    public User(String email, String firstName, String secondName,
                String phone, String userCity, String userStreet, String userZip) {
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.userCity = userCity;
        this.userStreet = userStreet;
        this.userZip = userZip;
    }

}
