package com.genz.rideapp.controller;

import com.genz.rideapp.model.Booking; // <--- Ye Jaruri hai
import com.genz.rideapp.model.Ride;
import com.genz.rideapp.model.User;
import com.genz.rideapp.repository.UserRepository;
import com.genz.rideapp.service.BookingService;
import com.genz.rideapp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List; // <--- Ye Jaruri hai

@Controller
public class PageController {

    @Autowired
    private RideService rideService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private com.genz.rideapp.repository.BookingRepository bookingRepository;

    // Home Page
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Signup Page
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    // Offer Ride Page
    @GetMapping("/offer-ride")
    public String offerRidePage() {
        return "offer-ride";
    }

    // Profile Page
    @GetMapping("/profile")
    public String profilePage(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        String email = principal.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        model.addAttribute("user", user);
        return "profile";
    }

    // My Rides & Bookings Dashboard
    @GetMapping("/my-rides")
    public String myRidesPage(Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userRepository.findByEmail(email).orElseThrow();

        // 1. Jo rides maine offer ki hain (As a Driver)
        List<Ride> offeredRides = rideService.getRidesByDriver(currentUser);
        
        // 2. Jo rides maine book ki hain (As a Passenger)
        List<Booking> myBookings = bookingService.getMyBookings(currentUser);

        model.addAttribute("offeredRides", offeredRides);
        model.addAttribute("myBookings", myBookings);
        
        return "my_rides";
    }

    // Search Results Page
    @GetMapping("/rides/search")
    public String searchRides(
            @RequestParam String source, 
            @RequestParam String destination, 
            @RequestParam(required = false) String filter,
            Model model) {
        
        List<Ride> rides;

        if ("cheapest".equals(filter)) {
            rides = rideService.searchRidesByPrice(source, destination);
        } else if ("earliest".equals(filter)) {
            rides = rideService.searchRidesByTime(source, destination);
        } else {
            rides = rideService.searchRides(source, destination);
        }
        
        model.addAttribute("ridesList", rides);
        model.addAttribute("source", source);
        model.addAttribute("destination", destination);
        
        return "rides";
    }

    // Chat Page
    @GetMapping("/chat")
    public String chatPage(@RequestParam Long withUserId, Model model) {
        User otherUser = userRepository.findById(withUserId)
                .orElseThrow(() -> new RuntimeException("User nahi mila"));
        
        model.addAttribute("otherUser", otherUser);
        return "chat";
    }

    // Map Tracking Page
    @GetMapping("/ride/track")
    public String trackRidePage(@RequestParam Long rideId, Model model) {
        Ride ride = rideService.getRideById(rideId);
        model.addAttribute("ride", ride);
        return "track_ride";
    }

    // Notifications Page
    @GetMapping("/notifications")
    public String notificationsPage(Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userRepository.findByEmail(email).orElseThrow();

        // Database se pucho: "Mere liye koi booking aayi hai kya?"
        List<Booking> myRideBookings = bookingRepository.findByRideDriver(currentUser);

        model.addAttribute("notifications", myRideBookings);
        
        return "notifications";
    }

}