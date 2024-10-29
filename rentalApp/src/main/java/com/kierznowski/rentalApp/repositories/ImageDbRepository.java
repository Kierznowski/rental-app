package com.kierznowski.rentalApp.repositories;

import com.kierznowski.rentalApp.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDbRepository extends JpaRepository<Image, Long> {
}
