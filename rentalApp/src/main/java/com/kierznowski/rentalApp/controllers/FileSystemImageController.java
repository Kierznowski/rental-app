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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("file-system")
@AllArgsConstructor
public class FileSystemImageController {

    FileLocationService fileLocationService;
    OfferService offerService;

    @PostMapping("/upload-images")
    ResponseEntity<List<Long>> uploadImage(@RequestParam("multipartImage") List<MultipartFile> images,
                                     @RequestParam("offerId") Long offerId) throws Exception {
        List<Long> imageIds = new ArrayList<>();

        for(MultipartFile image : images) {
            Long imageId = fileLocationService.save(image.getBytes(), image.getOriginalFilename());
            offerService.addImageToOffer(offerId, imageId);
        }
        return ResponseEntity.ok(imageIds);
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    FileSystemResource downloadImage(@PathVariable Long imageId) throws Exception {
        return fileLocationService.find(imageId);
    }
}
