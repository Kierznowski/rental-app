package com.kierznowski.rentalApp.controllers;


import com.kierznowski.rentalApp.repositories.FileSystemRepository;
import com.kierznowski.rentalApp.services.FileLocationService;
import com.kierznowski.rentalApp.services.OfferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class FileSystemImageControllerTest {

    private FileSystemImageController fileSystemImageController;
    private FileLocationService fileLocationService;
    private OfferService offerService;

    @BeforeEach
    void setUp() {
        fileLocationService = mock(FileLocationService.class);
        offerService = mock(OfferService.class);
        fileSystemImageController = new FileSystemImageController(fileLocationService, offerService);
    }

    @Test
    @DisplayName("Test uploading image within FileSystemImageController")
    void testUploadImage() throws Exception {

        Long offerId = 1L;
        MultipartFile mockImage1 = new MockMultipartFile("multipartImage", "image1.jpg", "image/jpeg", "image content 1".getBytes());
        MultipartFile mockImage2 = new MockMultipartFile("multipartImage", "image2.jpg", "image/jpeg", "image content 2".getBytes());
        List<MultipartFile> images = Arrays.asList(mockImage1, mockImage2);

        when(fileLocationService.save(any(byte[].class), anyString())).thenReturn(1L, 2L);

        ResponseEntity<HttpStatus> response = fileSystemImageController.uploadImage(images, offerId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(fileLocationService, times(2)).save(any(byte[].class), anyString());
        verify(offerService, times(2)).addImageToOffer(eq(offerId), anyLong());
    }

    @Test
    @Disabled("The test is not finished")
    @DisplayName("Test download image within FileSystemImageController")
    void testDownloadImage() throws Exception {
        Long imageId = 1L;
        //FileSystemResource mockResource = new FileSystemResource(new ByteArrayInputStream("image content".getBytes()));

       // when(fileLocationService.find(imageId)).thenReturn(mockResource);

        // When
        FileSystemResource resource = fileSystemImageController.downloadImage(imageId);

        // Then
        //assertEquals(mockResource, resource);
        verify(fileLocationService, times(1)).find(imageId);

    }
}
