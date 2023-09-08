package com.jwt.token.generation.controllers;


import com.jwt.token.generation.model.Response;
import com.jwt.token.generation.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@Slf4j
@RestController
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = fileStorageService.storeFile(file);
        log.info("fileName: " +fileName);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        log.info("fileDownloadUri: " +fileDownloadUri);
        return new Response(fileName, fileDownloadUri, file.getContentType(), file.getSize());
 }
}
