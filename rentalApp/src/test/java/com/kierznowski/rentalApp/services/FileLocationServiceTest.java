package com.kierznowski.rentalApp.services;


import com.kierznowski.rentalApp.models.Image;
import com.kierznowski.rentalApp.repositories.FileSystemRepository;
import com.kierznowski.rentalApp.repositories.ImageDbRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FileLocationServiceTest {

    @InjectMocks
    private FileLocationService fileLocationService;

    @Mock
    private FileSystemRepository fileSystemRepository;

    @Mock
    private ImageDbRepository imageDbRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test saving file method of FileLocationService")
    void testSave() throws Exception {
        byte[] imageContent = "test-image".getBytes();
        String imageName = "test-image.png";
        String testLocation = "/path/test-image.png";

        when(fileSystemRepository.save(imageContent, imageName)).thenReturn(testLocation);
        when(imageDbRepository.save(any(Image.class))).thenAnswer(invocation -> {
            Image image = invocation.getArgument(0);
            image.setId(1L);
            return image;
        });

        Long imageId = fileLocationService.save(imageContent, imageName);

        assertEquals(1L, imageId);
        verify(fileSystemRepository, times(1)).save(imageContent, imageName);

    }

    @Test
    @DisplayName("Test find file method of FileLocationService")
    void testFind() {
        Long imageId = 1L;
        String mockLocation = "/mock/path/test-image.png";
        Image mockImage = new Image("test-image.png", mockLocation);
        mockImage.setId(1L);

        when(imageDbRepository.findById(imageId)).thenReturn(Optional.of(mockImage));
        when(fileSystemRepository.findInFileSystem(mockLocation)).thenReturn(new FileSystemResource(mockLocation));

        FileSystemResource resource = fileLocationService.find(imageId);

        assertNotNull(resource);
        assertEquals(mockLocation, resource.getPath());
        verify(imageDbRepository, times(1)).findById(imageId);
        verify(fileSystemRepository, times(1)).findInFileSystem(mockLocation);
    }

    @Test
    @DisplayName("Test finding file with FileLocationService when file not found")
    void testFindThrowsExceptionWhenImageNotFound() {
        Long imageId = 1L;
        when(imageDbRepository.findById(imageId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> fileLocationService.find(imageId));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(imageDbRepository, times(1)).findById(imageId);
        verify(fileSystemRepository, never()).findInFileSystem(anyString());
    }

}
