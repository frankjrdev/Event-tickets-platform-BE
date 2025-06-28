package com.eventtickets.users.services;

import com.eventtickets.users.dto.RegisterUserDTO;
import com.eventtickets.users.model.User;
import com.eventtickets.users.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith( MockitoExtension.class )
class UserServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @InjectMocks private UserService userService;



    @Test
    void registerUser_WhenEmailAlreadyExists_ThrowsException() {
        // Given: A user registration request with an existing email
        RegisterUserDTO registerUserDTO = RegisterUserDTO.builder()
                .email("existing@example.com")
                .password("Password123!")
                .build();

         // When: The user service attempts to register the user
        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        // Then: An exception should be thrown
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(registerUserDTO);
        });

        assertEquals("Email already exists", exception.getMessage());
        verify(userRepository).existsByEmail("existing@example.com");
    }


}