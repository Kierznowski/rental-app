package com.kierznowski.rentalApp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    Long id;
    String location;
    String name;

    @Lob
    byte[] content;

    public Image(String imageName, String location) {
        this.name = imageName;
        this.location = location;
    }

    public Image(Long id) {
        this.id = id;
    }
}
