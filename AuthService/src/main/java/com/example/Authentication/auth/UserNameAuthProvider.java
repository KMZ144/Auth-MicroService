package com.example.Authentication.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.Authentication.repo.UserRepo;
import com.example.Authentication.service.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Component
public class UserNameAuthProvider implements AuthenticationProvider {
   

    private final UserDetailsServiceImpl detailsServiceImpl ;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        UserDetails userDetails = detailsServiceImpl.loadUserByUsername(userName);
        
        Authentication authenticated= new UsernameOnlyAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'supports'");
    }

}
