package com.example.Authentication.controller;

import com.example.Authentication.dto.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Authentication.dto.AuthRequest;
import com.example.Authentication.dto.AuthResponse;
import com.example.Authentication.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("")
    ResponseEntity<ResponseModel<AuthResponse>> login (@RequestBody AuthRequest authRequest){
        ResponseModel authResponse = authenticationService.login(authRequest);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/valid")
    public ResponseEntity<ResponseModel> validateToken(@RequestHeader("Authorization") String token ){
        ResponseModel validationResponse = authenticationService.validateToken(token);
        return ResponseEntity.ok(validationResponse);
    }
}
