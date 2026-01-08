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
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Bhejne wala
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne // Jisko bheja
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private String content; // Message kya hai
    
    private LocalDateTime timestamp; // Kab bheja
}