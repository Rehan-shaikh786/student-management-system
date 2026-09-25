package com.student.controller;

import com.student.dto.ApiResponse;
import com.student.dto.LoginRequest;
import com.student.dto.LoginResponse;
import com.student.entity.User;
import com.student.security.JwtService;
import com.student.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(
            UserService userService,
            JwtService jwtService) {

        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(
            @Valid @RequestBody RegisterRequest request) {

        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                "USER"
        );

        userService.registerUser(user);

        ApiResponse response = new ApiResponse(
                201,
                "User registered successfully."
        );

        return ResponseEntity
                .status(201)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        User user =
                userService.getUserByEmail(request.getEmail());

        if (user == null) {
            throw new IllegalArgumentException(
                    "Invalid email or password."
            );
        }

        boolean passwordMatches =
                userService.verifyPassword(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {
            throw new IllegalArgumentException(
                    "Invalid email or password."
            );
        }

        String token =
                jwtService.generateToken(
                        user.getEmail(),
                        user.getRole()
                );

        LoginResponse response =
                new LoginResponse(
                        200,
                        "Login successful.",
                        token,
                        user.getEmail(),
                        user.getRole()
                );

        return ResponseEntity.ok(response);
    }

    public static class RegisterRequest {

        @NotBlank(message = "Name is required")
        private String name;

        @NotBlank(message = "Email is required")
        @Email(message = "Enter a valid email address")
        private String email;

        @NotBlank(message = "Password is required")
        private String password;

        public RegisterRequest() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}