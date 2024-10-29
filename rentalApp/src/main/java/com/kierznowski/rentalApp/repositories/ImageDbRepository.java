package com.kierznowski.rentalApp.repositories;

import com.kierznowski.rentalApp.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDbRepository extends JpaRepository<Image, Long> {
}
