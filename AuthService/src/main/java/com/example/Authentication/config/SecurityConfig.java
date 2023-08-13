package com.example.Authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig  {

    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager(); 
	}

    @Bean
	protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.sessionManagement(
				session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.csrf(csrf -> csrf.disable())
		.cors(Customizer.withDefaults())
		.authorizeHttpRequests(auth -> auth.requestMatchers("api/v1/auth/**").permitAll())
		.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
		.exceptionHandling(auth -> auth.authenticationEntryPoint(
				(req,res,ex)->{
					res.sendError(HttpServletResponse.SC_UNAUTHORIZED,ex.getMessage());
				}));
		return httpSecurity.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("**/**"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}


	
    
}
