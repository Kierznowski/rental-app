package com.kierznowski.rentalApp.repositories;


import org.junit.jupiter.api.*;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemRepositoryTest {

    private FileSystemRepository fileSystemRepository;
    private String testResourcesDir;

    @BeforeEach
    void setUp() {
        fileSystemRepository = new FileSystemRepository();
        testResourcesDir = System.getProperty("java.io.tmpdir") + "/test-files/";
        fileSystemRepository.RESOURCES_DIR = testResourcesDir;
    }

    @AfterEach
    void tearDown() throws Exception {
        Path testDir = Paths.get(testResourcesDir);
        if(Files.exists(testDir)) {
            Files.walk(testDir).map(Path::toFile).forEach(File::delete);
        }
    }

    @Test
    @DisplayName("Test saving image within FileSystemRepository")
    void testSave() throws Exception {
        byte[] content = "Sample content".getBytes();
        String imageName = "test-image.png";

        String savedFilePath = fileSystemRepository.save(content, imageName);

        assertNotNull(savedFilePath);
        assertTrue(Files.exists(Paths.get(savedFilePath)));
        assertEquals("Sample content", Files.readString(Paths.get(savedFilePath)));
    }

    @Test
    @DisplayName("Test finding image in FileSystemRepository")
    void testFindInFileSystem() throws Exception {
        byte[] content = "Sample content".getBytes();
        String imageName = "test-image.png";
        String savedFilePath = fileSystemRepository.save(content, imageName);

        FileSystemResource resource = fileSystemRepository.findInFileSystem(savedFilePath);

        assertNotNull(resource);
        assertTrue(resource.exists());
        assertEquals("Sample content", Files.readString(resource.getFile().toPath()));
    }

    @Test
    @DisplayName("Test if FileSystemRepository throws exception for invalid file path")
    void testFindInFileSystemThrowsExceptionForInvalidPath() {
        String invalidPath = testResourcesDir + "non-existed.png";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> fileSystemRepository.findInFileSystem(invalidPath));

        assertNotNull(exception);
        assertEquals("File not found: " + invalidPath, exception.getMessage());
    }

}
