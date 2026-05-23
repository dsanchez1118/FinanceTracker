package com.financetracker.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Handle Cross-Origin Requests (React to Spring Boot)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. Disable CSRF (Standard practice for stateless REST APIs using React)
                .csrf(csrf -> csrf.disable())

                // 3. Define URL authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Allow anyone to access the H2 database console for debugging
                        .requestMatchers("/h2-console/**").permitAll()
                        // Require authentication for your Plaid/Transaction endpoints
                        .requestMatchers("/api/transactions/**", "/api/plaid/**").authenticated()
                        // For everything else, allow public access or configure as needed
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        // 4. Required if you want to use the H2 console UI inside frames
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    // 5. Configure CORS so your React app running on localhost:3000 can talk to Spring
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Your React URL
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
