package com.genz.rideapp.model;

import java.time.LocalDate;

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
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;      // Kahan se (e.g., Delhi)
    private String destination; // Kahan tak (e.g., Gurugram)
    
    private LocalDate travelDate; // Kab jana hai
    private java.time.LocalTime startTime; // kis time jana hai puchne ke liye 
    private Double price;       // Kiraya
    
    private Integer seatsAvailable; // Kitni seats khali hain
    
    private Double sourceLat;  // Source Latitude
    private Double sourceLng;  // Source Longitude
    private Double destLat;    // Destination Latitude
    private Double destLng;    // Destination Longitude
  
    // Sabse Important: Relationship
    // Ek Ride ka ek hi Driver (User) hota hai.
    // @ManyToOne ka matlab: Many Rides can belong to One User.
    @ManyToOne 
    @JoinColumn(name = "driver_id") // Database me 'driver_id' naam ka column banega
    private User driver; 
}