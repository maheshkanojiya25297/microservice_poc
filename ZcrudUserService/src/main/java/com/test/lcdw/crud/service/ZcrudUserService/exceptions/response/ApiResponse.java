package com.test.lcdw.crud.service.ZcrudUserService.exceptions.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private String message;
    private String success;
    private HttpStatus status;
}
