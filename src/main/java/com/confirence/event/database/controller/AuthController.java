package com.confirence.event.database.controller;


import com.confirence.event.database.dto.UserDTO;
import com.confirence.event.database.model.User;
import com.confirence.event.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO.getEmail(), userDTO.getPw(), userDTO.getUsername());
        userService.registerUser(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        // Here you will implement JWT authentication later
        return "Login endpoint (to implement JWT)";
    }

}
