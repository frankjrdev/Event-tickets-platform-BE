package com.eventtickets.users.services;

import com.eventtickets.users.config.jwt.JwtService;
import com.eventtickets.users.dto.AuthResponseDTO;
import com.eventtickets.users.dto.LoginRequestDTO;
import com.eventtickets.users.dto.UserDTO;
import com.eventtickets.users.model.User;
import com.eventtickets.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDTO authenticate(LoginRequestDTO request) {
        User user = userRepository.findByEmail( request.email() )
                .orElseThrow( () -> new IllegalArgumentException("User not found check your credentials") );

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException( "Invalid credentials" );
        }

        String token = jwtService.generateToken(UserDTO.toDTO( user ));

        return new AuthResponseDTO(
                token,
                UserDTO.toDTO( user )
        );
    }

    public AuthResponseDTO registerUser(UserDTO newUser) {
        if (userRepository.existsByEmail(newUser.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .email(newUser.email())
//                .password(passwordEncoder.encode(newUser.password()))
                .firstName(newUser.firstName())
                .lastName(newUser.lastName())
                .phoneNumber(newUser.phoneNumber())
                .creditCardNumber(newUser.creditCardNumber())
                .isAdmin(newUser.isAdmin())
                .build();

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(UserDTO.toDTO(savedUser));

        return new AuthResponseDTO(
                token,
                UserDTO.toDTO(savedUser)
        );
    }
}
