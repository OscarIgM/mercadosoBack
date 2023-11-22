package com.example.MercadosoBack.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
@CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}