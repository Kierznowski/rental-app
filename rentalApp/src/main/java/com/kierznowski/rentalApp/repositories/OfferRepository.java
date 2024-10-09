package com.kierznowski.rentalApp.repositories;

import com.kierznowski.rentalApp.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
