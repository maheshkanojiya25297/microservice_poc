package com.jwt.token.generation.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {
    private String fileName;
    private String fileDowmloadUri;
    private String fileType;
    private long size;
}
