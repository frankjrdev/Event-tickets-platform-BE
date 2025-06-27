package com.eventtickets.users.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterUserDTO(
        @NotBlank String name,
        @Email @NotBlank String email,
        @NotBlank String password,
        String firstName,
        String lastName,
        String phoneNumber,
        String creditCardNumber,
        boolean isAdmin
){}
