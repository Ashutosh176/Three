package com.genz.rideapp.repository;

import com.genz.rideapp.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.genz.rideapp.model.User; // <--- YE MISSING THA

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    
    // Simple Search
    List<Ride> findBySourceAndDestination(String source, String destination);

    // 1. Sort by Price (Low to High)
    List<Ride> findBySourceAndDestinationOrderByPriceAsc(String source, String destination);

    // 2. Sort by Time (Jaldi wali pehle) -> Date then Time
    List<Ride> findBySourceAndDestinationOrderByTravelDateAscStartTimeAsc(String source, String destination);

    List<Ride> findByDriver(User driver);
}

