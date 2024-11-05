package com.kierznowski.rentalapp.client.model;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class User {

    private Long userId;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String userCity;
    private final String userStreet;
    private final String userZip;
}


