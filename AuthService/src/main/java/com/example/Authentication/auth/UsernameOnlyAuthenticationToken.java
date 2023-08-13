package com.example.Authentication.auth;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.example.Authentication.dto.UserDetailsImpl;
import lombok.AllArgsConstructor;


public class UsernameOnlyAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    public UsernameOnlyAuthenticationToken(UserDetails userDetails, Object principal,
            Collection<? extends GrantedAuthority> authorities) {
                super(authorities);  
                this.principal=principal;  
                setAuthenticated(true);

    }

    @Override
    public Object getCredentials() {
       return null;
    }

    @Override
    public Object getPrincipal() {
       return  this. principal;
    }

}
