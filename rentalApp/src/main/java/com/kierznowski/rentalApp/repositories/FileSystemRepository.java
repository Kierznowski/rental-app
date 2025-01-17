package com.kierznowski.rentalApp.repositories;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Repository
public class FileSystemRepository {

    String RESOURCES_DIR = FileSystemRepository.class.getResource("/").getPath().replace(":", "");

    public String save(byte[] content, String imageName) throws Exception {
        Path newFile = Paths.get(RESOURCES_DIR + new Date().getTime() + "-" + imageName);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);

        return newFile.toAbsolutePath().toString();
    }

    public FileSystemResource findInFileSystem(String location) {
        Path path = Paths.get(location);
        if(!Files.exists(path)) {
            throw new RuntimeException("File not found: " + location);
        }
        return new FileSystemResource(path);
    }
}
