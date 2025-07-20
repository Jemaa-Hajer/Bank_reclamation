package com.HJ.BankReclamation.controller;

import com.HJ.BankReclamation.dtos.ApiResponse;
import com.HJ.BankReclamation.dtos.ClientDto;
import com.HJ.BankReclamation.dtos.ClientResponseDto;
import com.HJ.BankReclamation.dtos.LoginRequestDto;
import com.HJ.BankReclamation.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@Valid @RequestBody ClientDto clientDto){
        authService.signup(clientDto);
        Map<String,String> response = new HashMap<>();
        response.put("message","Client created successfully");
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<ClientResponseDto>> login (@Valid @RequestBody LoginRequestDto loginRequestDto){
        try {
            ClientResponseDto response =authService.login(loginRequestDto);
            ApiResponse<ClientResponseDto> apiResponse = new ApiResponse<>("success","Login successful", response);

            return ResponseEntity.ok(apiResponse);
        } catch (IllegalArgumentException e) {
            ApiResponse<ClientResponseDto> apiError = new ApiResponse<>("error","Login unsuccessful", null);
            return ResponseEntity.status(403).body(apiError);
        }


    }

}
