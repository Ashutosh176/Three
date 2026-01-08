package com.genz.rideapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genz.rideapp.model.Booking;
import com.genz.rideapp.model.User;
import com.genz.rideapp.repository.UserRepository;
import com.genz.rideapp.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private UserRepository userRepository;

    // Ab URL me sirf rideId chahiye, passengerId nahi
    @PostMapping("/book")
    public ResponseEntity<?> bookRide(@RequestParam Long rideId, Principal principal) {
        try {
            // 1. Logged-in user (Passenger) ko dhundo
            String email = principal.getName();
            User passenger = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // 2. Booking Service call karo (Passenger ki ID pass karke)
            Booking booking = bookingService.bookRide(rideId, passenger.getId());
            
            return ResponseEntity.ok("Booking Confirmed! Booking ID: " + booking.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}