package com.kierznowski.rentalApp.services;

import com.kierznowski.rentalApp.models.Image;
import com.kierznowski.rentalApp.repositories.FileSystemRepository;
import com.kierznowski.rentalApp.repositories.ImageDbRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class FileLocationService {

    FileSystemRepository fileSystemRepository;
    ImageDbRepository imageDbRepository;

    public Long save(byte[] bytes, String imageName) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);

        return imageDbRepository.save(new Image(imageName, location)).getId();
    }

    public FileSystemResource find(Long imageId) {
        Image image = imageDbRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(image.getLocation());
    }
}