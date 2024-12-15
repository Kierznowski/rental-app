package com.kierznowski.rentalapp.client.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class UserBasicInfoDTO {
    private Long userId;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String userCity;
    private final String userStreet;
    private final String userZip;
}
