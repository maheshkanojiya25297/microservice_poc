package com.implemented.hotel.service.payload;

import ch.qos.logback.core.status.Status;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private String message;
    private boolean Success;
    private HttpStatus Status;

}
