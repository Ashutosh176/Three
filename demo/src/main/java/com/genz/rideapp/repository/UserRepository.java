package com.genz.rideapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genz.rideapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository ne hume free me methods de diye: save(), findAll(), delete() etc.
    
    // Custom method: Email se user dhundne ke liye
    Optional<User> findByEmail(String email);
}