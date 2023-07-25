package com.lmplemented.gateway.ApiGateway.model;

import lombok.*;

import java.util.Collection;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String userId;

    private String accessToken;

    private String refreshToken;

    private long expireAt;

    private Collection<String> authorities;
}
