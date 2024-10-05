package com.sparkshare.demo.controller;

import com.sparkshare.demo.dto.AuthRequest;
import com.sparkshare.demo.dto.TokenResponse;
import com.sparkshare.demo.dto.UserRegisterationRequest;
import com.sparkshare.demo.model.User;
import com.sparkshare.demo.repository.UserRepository;
import com.sparkshare.demo.security.JwtUtil;
import com.sparkshare.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // User Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthRequest authRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(token);
        tokenResponse.setAuthenticationType("Bearer");
        return ResponseEntity.ok(tokenResponse);
    }

    // User Registration Endpoint
    @PostMapping("/register")
    public User register(@RequestBody UserRegisterationRequest user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail((user.getEmail()));
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return userRepository.save(newUser);
    }
}
