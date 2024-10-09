package com.kierznowski.rentalApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name="Offer_data")
@RequiredArgsConstructor
public class Offer {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Date createdAt = new Date();
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
