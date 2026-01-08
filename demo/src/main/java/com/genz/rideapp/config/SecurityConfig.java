package com.genz.rideapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Development ke liye band kar rahe hain
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/signup", "/login", "/css/**", "/js/**", "/api/auth/**").permitAll() // Ye sab khula rahega
                .anyRequest().authenticated() // Baaki sabke liye Login jaruri hai
            )
            .formLogin(form -> form
                .loginPage("/login") // Humara custom login page
                .defaultSuccessUrl("/", true) // Login ke baad Home par bhejo
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/") // Logout ke baad wapas Home
                .permitAll()
            );

        return http.build();
    }

    // Password ko Encrypt karne ke liye (Security Best Practice)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}