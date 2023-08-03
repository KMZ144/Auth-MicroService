package com.example.Authentication.service;

import com.example.Authentication.dto.ResponseModel;
import com.example.Authentication.dto.ValidationResponse;
import com.example.Authentication.repo.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.Authentication.dto.AuthRequest;
import com.example.Authentication.dto.AuthResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;

    public  ResponseModel<AuthResponse> login (AuthRequest authRequest){
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return new ResponseModel(authResponse);
    }

    public ResponseModel<ValidationResponse> validateToken (String token){
        String jwtToken = token.substring(7);
        boolean isValidToken =  jwtService.validateToken(jwtToken);
        ValidationResponse validationResponse = new ValidationResponse(isValidToken);
        return new ResponseModel<>(validationResponse);
    }
}
