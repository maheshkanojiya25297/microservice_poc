package com.jwt.token.generation.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JwtResponse {

    private String jwtToken;
    private String username;

}
