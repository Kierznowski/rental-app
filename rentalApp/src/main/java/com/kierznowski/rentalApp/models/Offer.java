package com.kierznowski.rentalApp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="Offer_data")
@RequiredArgsConstructor
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @ElementCollection
    @CollectionTable(
            name = "offer_images",               // Table to store image IDs
            joinColumns = @JoinColumn(name = "offer_id") // Foreign key in offer_images referencing Offer_data
    )
    @Column(name = "image_id")
    private List<Long> imageIds = new ArrayList<>();
}
