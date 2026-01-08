package com.genz.rideapp.service;

import com.genz.rideapp.model.Booking;
import com.genz.rideapp.model.Ride;
import com.genz.rideapp.model.User;
import com.genz.rideapp.repository.BookingRepository;
import com.genz.rideapp.repository.RideRepository;
import com.genz.rideapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List; // <--- YE MISSING THA

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking bookRide(Long rideId, Long passengerId) {
    Ride ride = rideRepository.findById(rideId).orElseThrow();
    
    // 1. Check: Khud ki ride book nahi kar sakte
    if(ride.getDriver().getId().equals(passengerId)) {
        throw new RuntimeException("Bro, apni hi ride book karoge? 😂");
    }

    // 2. Check: Seats available hain ya nahi
    if(ride.getSeatsAvailable() <= 0) {
        throw new RuntimeException("Sorry, Housefull ho gaya! 🚫");
    }

    // 3. Booking Create karo
    User passenger = userRepository.findById(passengerId).orElseThrow();
    Booking booking = new Booking();
    booking.setRide(ride);
    booking.setPassenger(passenger);
    booking.setStatus("CONFIRMED");
    booking.setBookingTime(LocalDateTime.now());

    // 4. Ride me Seats kam karo (UPDATE)
    ride.setSeatsAvailable(ride.getSeatsAvailable() - 1);
    rideRepository.save(ride); // Updated ride save karo

    return bookingRepository.save(booking);
}
    

    // Passenger ki bookings lana
    public List<Booking> getMyBookings(User passenger) {
        return bookingRepository.findByPassenger(passenger);
    }
}