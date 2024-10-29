package com.kierznowski.rentalapp.client.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Offer {

    private Long id;
    private Long userId;
    private Date createdAt;
    private String offerName;
    private String city;
    private String district;
    private String street;
    private String zip;
    private int buildingYear;
    private BigDecimal fullPrice;
    private BigDecimal basePrice;
    private BigDecimal additionalPrice;
    private int area;
    private int roomsNumber;
    private int estateLevel;
    private boolean garage;
    private boolean annexKitchen;
    private boolean elevator;
    private boolean animals;
}
