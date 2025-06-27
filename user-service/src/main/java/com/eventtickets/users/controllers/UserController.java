package com.eventtickets.users.controllers;

import com.eventtickets.users.dto.RegisterUserDTO;
import com.eventtickets.users.dto.UserDTO;
import com.eventtickets.users.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO registerUser(@RequestBody @Valid RegisterUserDTO newUser) {
        return userService.registerUser(newUser);
    }
}
