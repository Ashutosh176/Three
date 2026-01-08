package com.genz.rideapp.model;

import jakarta.persistence.*;
import lombok.Data; // Code short karne ke liye
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity // Ye batata hai ki ye class Database ki Table banegi
@Table(name = "users") // Table ka naam 'users' hoga
@Data // Ye automatic Getters, Setters, aur toString bana dega
@NoArgsConstructor // Khali constructor banayega
@AllArgsConstructor // Full constructor banayega
public class User {

    @Id // Ye Primary Key hai (Unique ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID automatic badhti jayegi (1, 2, 3...)
    private Long id;

    private String name;

    @Column(unique = true) // Email sabka alag hona chahiye
    private String email;

    private String password;

    private String phoneNumber;

    // Role: "DRIVER" ya "PASSENGER"
    private String role; 
    
    // Profile pic URL (future me kaam aayega aesthetic UI ke liye)
    private String profilePicUrl;
}