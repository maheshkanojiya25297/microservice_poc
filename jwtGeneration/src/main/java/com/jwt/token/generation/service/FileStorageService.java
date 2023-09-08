package com.jwt.token.generation.service;

import com.jwt.token.generation.model.FileStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class FileStorageService {

    private final Path fileStoreLocation;

    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStoreLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
    }


    public String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("fileName: " + fileName);
        Path targetLocation = this.fileStoreLocation.resolve(fileName);
        log.info("target Location:" + targetLocation);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
}
