package com.example.Authentication.dto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import com.example.Authentication.model.Role;
import com.example.Authentication.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Slf4j
public class UserDetailsImpl implements UserDetails {

    private String email;
    private Role role;
     private LocalDateTime lastLogin;
    
   // private boolean isActive;

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    // public boolean isActive() {
    //     return isActive;
    // }
  

   private  Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.email = user.getEmail();
        this.role = user.getRole();
        this.lastLogin= user.getLastLogin();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(this.getRole());
        return List.of(new SimpleGrantedAuthority(""+this.getRole()));
    }

    @Override
    public String getPassword() {
        return null;
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
