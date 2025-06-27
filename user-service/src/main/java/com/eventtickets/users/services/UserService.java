package com.eventtickets.users.services;

import com.eventtickets.users.dto.RegisterUserDTO;
import com.eventtickets.users.dto.UserDTO;
import com.eventtickets.users.model.User;
import com.eventtickets.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.eventtickets.users.dto.UserDTO.toDTO;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEcodeer;


    /**
     * Registers a new user.
     *
     * @param newUser the details of the user to register
     * @return the registered user's details
     */
    public UserDTO registerUser(RegisterUserDTO newUser) {
      if( userRepository.existsByEmail(newUser.email()) ) {
          throw new IllegalArgumentException("Email already exists");
      }

      User user = User.builder()
        .email(newUser.email())
        .password(newUser.password())
        .firstName(newUser.firstName())
        .lastName(newUser.lastName())
        .phoneNumber(newUser.phoneNumber())
        .creditCardNumber(newUser.creditCardNumber())
        .isAdmin(newUser.isAdmin())
        .build();

      User savedUser = userRepository.save(user);

      return toDTO(savedUser);
    }



}
