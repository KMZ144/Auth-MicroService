package com.example.Authentication.service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.example.Authentication.model.User;
import com.example.Authentication.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
	
	@Value("${jwt.secret}")
	private String secret ;

	private final UserRepo userRepo;

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
    //for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		
		Map<String, Object> claims = new HashMap<>();
		// userDetails.getAuthorities()
		// .forEach(authority -> claims.put("roles", authority.getAuthority()));
		return doGenerateToken(claims, userDetails.getUsername());
	}

		private String doGenerateToken(Map<String, Object> claims, String subject) {

			String encodedString = Base64.getEncoder().encodeToString(secret.getBytes());
        return Jwts.builder().setClaims(claims).setSubject(subject)
		// .setIssuedAt(new Date(System.currentTimeMillis()))
                // .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, encodedString ).compact();
    }

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()));
	}

	public Boolean validateToken(String token) {
		final String userNameInToken = getUsernameFromToken(token);
		User user = userRepo.findByEmail(userNameInToken)
				.orElseThrow(()-> new EntityNotFoundException("not Found Entity"));
		return (userNameInToken.equals(user.getEmail()));
	}
}

