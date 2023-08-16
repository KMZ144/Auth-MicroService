package com.example.Authentication.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.Authentication.service.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UserNameAuthProvider implements AuthenticationProvider {
   

    private final UserDetailsServiceImpl detailsServiceImpl ;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        UserDetails userDetails = detailsServiceImpl.loadUserByUsername(userName);
        Authentication authenticated= new UsernameOnlyAuthenticationToken(userDetails, userDetails.getAuthorities());
        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
      return authentication.equals(UsernameOnlyAuthenticationToken.class);
    }

}
