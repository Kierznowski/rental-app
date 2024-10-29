package com.kierznowski.rentalApp.controllers;

import com.kierznowski.rentalApp.models.Offer;
import com.kierznowski.rentalApp.services.FileLocationService;
import com.kierznowski.rentalApp.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("file-system")
public class FileSystemImageController {

    @Autowired
    FileLocationService fileLocationService;

    @Autowired
    OfferService offerService;

    @PostMapping("/image")
    ResponseEntity<Long> uploadImage(@RequestParam("multipartImage") MultipartFile image,
                                     @RequestParam("offerId") Long offerId) throws Exception {
        Long imageId =  fileLocationService.save(image.getBytes(), image.getOriginalFilename());
        offerService.addImageToOffer(offerId, imageId);
        return ResponseEntity.ok(imageId);
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    FileSystemResource downloadImage(@PathVariable Long imageId) throws Exception {
        return fileLocationService.find(imageId);
    }
}
