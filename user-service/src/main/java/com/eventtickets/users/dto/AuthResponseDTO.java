package com.eventtickets.users.dto;


import lombok.Builder;

@Builder
public record AuthResponseDTO(
        String token,
        UserDTO user
) {}
