package com.kierznowski.rentalapp.client.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageService {

    RestTemplate restTemplate;

    public ImageService() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<HttpStatus> uploadImages(Object request) {
        return restTemplate.postForEntity("httplocalhost:8080/files-system/upload-images", request, HttpStatus.class);
    }

}
