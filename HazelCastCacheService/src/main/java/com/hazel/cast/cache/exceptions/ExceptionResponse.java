package com.hazel.cast.cache.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {

    public String message;
    public String success;
    public HttpStatus status;
}
