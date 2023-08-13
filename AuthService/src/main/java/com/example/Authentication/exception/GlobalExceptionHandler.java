package com.example.Authentication.exception;

import com.example.Authentication.dto.ResponseModel;
import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseModel> handleBadCredentialsException(BadCredentialsException ex){
        log.info("exception called");
         return handleExceptions(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ResponseModel> handleJwtException(JwtException ex){
        return handleExceptions(HttpStatus.BAD_REQUEST,ex.getMessage());
    }
    private ResponseEntity<ResponseModel> handleExceptions(HttpStatus status,String message){
        ResponseModel responseModel = new ResponseModel(message);
        return ResponseEntity.status(status).body(responseModel);
    }
}
