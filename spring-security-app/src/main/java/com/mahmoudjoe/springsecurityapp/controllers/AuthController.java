package com.mahmoudjoe.springsecurityapp.controllers;

import com.mahmoudjoe.springsecurityapp.dto.AuthRequest;
import com.mahmoudjoe.springsecurityapp.repo.UserRepo;
import com.mahmoudjoe.springsecurityapp.services.JwtUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );
        final UserDetails user = userRepo.findUserByUserName(authRequest.getUsername());
        if(user!=null){
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("some error has occurred");
    }
}
