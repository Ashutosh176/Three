package com.genz.rideapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genz.rideapp.model.Ride; // <-- Ye jaruri hai
import com.genz.rideapp.model.User;
import com.genz.rideapp.repository.UserRepository;
import com.genz.rideapp.service.RideService;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public Ride createRide(@RequestBody Ride ride, Principal principal) {
        // 1. Principal se logged-in user ka Email nikalo
        String email = principal.getName();
        
        // 2. Email se User dhundo
        User driver = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Driver nahi mila!"));

        // 3. Ride ke saath ye Driver set karo
        ride.setDriver(driver);
        
        return rideService.publishRide(ride);
    }
}