package com.implementation.multiDb.connectivity.multiple.Db.Connectivity.exceptions.response;

import lombok.*;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExResponse {

    private String message;
    private String sucess;
    private HttpStatus status;

}
