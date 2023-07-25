package com.test.lcdw.crud.service.ZcrudUserService.response;

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
