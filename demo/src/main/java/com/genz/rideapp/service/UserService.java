package com.genz.rideapp.service;

import com.genz.rideapp.model.User;
import com.genz.rideapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // <-- Ye Security wala tool hai

    // 1. Sign Up Logic (Yahan Galti Thi Shayad)
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }
        
        // --- YE LINE SABSE JARURI HAI ---
        // Password ko encrypt karke wapas user me set karo
        user.setPassword(passwordEncoder.encode(user.getPassword())); 
        // -------------------------------

        // Role set karo (Default USER)
        user.setRole("USER");
        
        return userRepository.save(user);
    }

    // 2. Login Logic
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User nahi mila"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}