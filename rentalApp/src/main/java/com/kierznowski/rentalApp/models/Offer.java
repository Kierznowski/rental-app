package com.kierznowski.rentalApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="Offer_data")
@EqualsAndHashCode(exclude = "user")
@RequiredArgsConstructor
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotNull
    private String offerName;

    @NotNull
    private String city;
    private String district;
    private String street;
    private String zip;

    @Min(1800)
    private int buildingYear;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal fullPrice;

    @DecimalMin("0.0")
    private BigDecimal basePrice;

    @DecimalMin("0.0")
    private BigDecimal additionalPrice;

    @Min(0)
    private int area;

    @Min(1)
    private int roomsNumber;

    @Min(0)
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

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
