package com.eventtickets.users.controllers;

import com.eventtickets.users.dto.AuthResponseDTO;
import com.eventtickets.users.dto.LoginRequestDTO;
import com.eventtickets.users.dto.UserDTO;
import com.eventtickets.users.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag( name = "Authentication", description = "Endpoints for user authentication" )
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation( summary = "Authenticate user and return JWT token" )
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO request
            ){
        return ResponseEntity.ok(authService.authenticate( request ));
    }


    @PostMapping("/register")
    @Operation( summary = "Register a new user and return JWT token" )
    public ResponseEntity<AuthResponseDTO> registerUser(
            @RequestBody @Valid UserDTO newUser
            ){
        return ResponseEntity.ok(authService.registerUser( newUser ));
    }

}
