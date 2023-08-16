package com.example.Authentication.service;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.management.relation.Role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
	
	@Value("${jwt.secret}")
	private String secret ;
	@Value("${jwt.validity}")
	private int JWT_TOKEN_VALIDITY;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(secret.getBytes())
				.build().parseClaimsJws(token)
				.getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(Authentication userDetails) {
		
		Map<String, Object> claims = new HashMap<>();
		 userDetails.getAuthorities()
		 .forEach(authority -> claims.put("roles", authority.getAuthority()));
		return doGenerateToken(claims, userDetails.getName() , userDetails.getAuthorities());
	}

		private String doGenerateToken(Map<String, Object> claims, String subject, Collection<? extends GrantedAuthority> collection ) {

        return Jwts.builder().setClaims(claims).setSubject(subject)
		 		.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
    }

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()));
	}

}

