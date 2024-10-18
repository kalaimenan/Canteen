package com.rome.canteen.controller;

import com.rome.canteen.model.AuthenticationRequest;
import com.rome.canteen.model.AuthenticationResponse;
import com.rome.canteen.model.User;
import com.rome.canteen.service.JwtUtil;
import com.rome.canteen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // Load user details
            final UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
            User user = userService.findByEmail(request.getEmail());

            // Generate JWT token with email and role
            final String jwt = jwtUtil.generateToken(userDetails, user.getEmail(), user.getRole());

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentication failed: " + e.getMessage());
        }
    }





    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {


            // Assign default role if not provided
            if (user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("user");
            }

            // Register the new user
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Signup failed: " + e.getMessage());
        }
    }

}
