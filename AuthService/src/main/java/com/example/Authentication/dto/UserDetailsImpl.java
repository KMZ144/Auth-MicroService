package com.example.Authentication.dto;

import java.util.Collection;

import com.example.Authentication.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{

    private String email;

    private String password;

    public UserDetailsImpl(User user){
        this.email=user.getEmail();
        this.password=user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     return null ;
    }

    @Override
    public String getPassword() {
        return this.password;
       }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
