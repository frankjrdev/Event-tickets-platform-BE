package com.eventtickets.users.dto;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDTO(
        String id,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String creditCardNumber,
        boolean isAdmin,
        LocalDateTime createdAt
) {


    public static UserDTO toDTO(com.eventtickets.users.model.User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .creditCardNumber(user.getCreditCardNumber())
                .isAdmin(user.isAdmin())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
