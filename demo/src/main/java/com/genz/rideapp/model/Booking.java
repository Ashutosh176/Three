package com.genz.rideapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Ek User (Passenger) kayi bookings kar sakta hai
    @JoinColumn(name = "passenger_id")
    private User passenger;

    @ManyToOne // Ek Ride me kayi bookings ho sakti hain
    @JoinColumn(name = "ride_id")
    private Ride ride;

    private LocalDateTime bookingTime; // Kab book kiya
    
    private String status; // CONFIRMED, CANCELLED
}