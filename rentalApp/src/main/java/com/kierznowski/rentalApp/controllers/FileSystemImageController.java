package com.kierznowski.rentalApp.controllers;

import com.kierznowski.rentalApp.services.FileLocationService;
import com.kierznowski.rentalApp.services.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file-system/image")
@AllArgsConstructor
public class FileSystemImageController {

    FileLocationService fileLocationService;
    OfferService offerService;

    @PostMapping
    ResponseEntity<Long> uploadImage(@RequestParam("multipartImage") MultipartFile image,
                                     @RequestParam("offerId") Long offerId) throws Exception {
        Long imageId = fileLocationService.save(image.getBytes(), image.getOriginalFilename());
        offerService.addImageToOffer(offerId, imageId);

        return ResponseEntity.ok(imageId);
    }

    @GetMapping(value = "/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    FileSystemResource downloadImage(@PathVariable Long imageId) throws Exception {
        return fileLocationService.find(imageId);
    }
}
