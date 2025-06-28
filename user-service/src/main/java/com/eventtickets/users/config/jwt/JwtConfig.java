package com.eventtickets.users.config.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value( "${jwt.secret}" )
    private String secret;

    @Value( "${jwt.expiration}" )
    private long expiration;

    @Bean
    public JwtService jwtService() {
        return new JwtService( secret, expiration );
    }
}
