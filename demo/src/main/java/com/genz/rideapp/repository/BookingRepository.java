package com.genz.rideapp.repository;

import com.genz.rideapp.model.Booking;
import com.genz.rideapp.model.User; // <--- YE MISSING THA
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    // Passenger ki bookings nikalne ke liye
    // Note: Method ka naam 'findByPassenger' hona chahiye kyunki Booking.java me variable ka naam 'passenger' hai
    List<Booking> findByPassenger(User passenger);
    List<Booking> findByRideDriver(User driver) ;
}