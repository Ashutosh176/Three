package com.genz.rideapp.controller;

import com.genz.rideapp.model.User;
import com.genz.rideapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user) {
        // --- DEBUGGING PRINTS ---
        System.out.println("---------- SIGNUP REQUEST AAYI ----------");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        // ------------------------

        userService.registerUser(user);
        
        System.out.println("---------- USER SAVED SUCCESSFULLY ----------");
        
        return "redirect:/login?success";
    }
}