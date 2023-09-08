package com.jwt.token.generation.model;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    public String uploadDir;
}
