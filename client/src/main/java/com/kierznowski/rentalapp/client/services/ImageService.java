package com.kierznowski.rentalapp.client.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageService {

    @Value("${resource.url.image.upload}")
    String uploadUrl;

    RestTemplate restTemplate;

    public ImageService() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<HttpStatus> uploadImages(Object request) {
        return restTemplate.postForEntity(uploadUrl, request, HttpStatus.class);
    }

}
