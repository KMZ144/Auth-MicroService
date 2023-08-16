package com.example.Authentication.service;

import com.example.Authentication.dto.ResponseModel;
import com.example.Authentication.dto.UserDetailsImpl;
import com.example.Authentication.model.User;
import com.example.Authentication.repo.UserRepo;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Authentication.auth.UserNameAuthProvider;
import com.example.Authentication.auth.UsernameOnlyAuthenticationToken;
import com.example.Authentication.dto.AuthRequest;
import com.example.Authentication.dto.AuthResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
//    private final UserNameAuthProvider authProvider;

    private  final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;

    public ResponseModel<AuthResponse> login(AuthRequest authRequest) {
        String email = authRequest.getEmail();
        // authProvider = new UserNameAuthProvider(userDetailsService);
        // authProvider= new UserNameAuthProvider(userDetailsService);
//        updateLastLogin(email);
//        authProvider.authenticate(authentication);
        Authentication authentication = authenticationManager.authenticate(new UsernameOnlyAuthenticationToken(email));
//        UserDetails user = (UserDetails) authentication.getPrincipal();
//        log.info(user.toString());
//        UserDetails details = userDetailsService.loadUserByUsername(email);
        String token =
                jwtService.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token);
        return new ResponseModel(authResponse);
    }

    // private void checkUserValidality(UserDetailsImpl userDetails) {
    //   //  boolean isActive = userDetails.isActive();
    //     LocalDateTime latestValidLoginTime = userDetails.getLastLogin().plusMonths(1L);
    //     if (!(isActive && checkLastLogin(latestValidLoginTime)))
    //         throw new RuntimeException("user is not valid");

    // }

    private boolean checkLastLogin(LocalDateTime latestValidLoginTime) {
        return latestValidLoginTime.isAfter(LocalDateTime.now());
    }

    public void updateLastLogin(String email) {

        final User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("UserName not found "));
        // user.setActive(checkLastLogin(user.getLastLogin().withMonth(2)));

        boolean active = checkLastLogin(user.getLastLogin().withSecond(2));
        //user.setActive(active);
        if (active == true){
      //   log.info("Hi Before setteing Last login date");

            user.setLastLogin(LocalDateTime.now());

            log.info("Hi After setteing Last login date and time");
            }
            //23:41:53.797418
            //23:41:53.797418
        userRepo.save(user);
    }

}
