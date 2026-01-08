package com.genz.rideapp.service;

import com.genz.rideapp.model.Ride;
import com.genz.rideapp.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.genz.rideapp.model.User; // <--- YE MISSING THA


@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    // Ride Save karna
    public Ride publishRide(Ride ride) {
        return rideRepository.save(ride);
    }

    // Saari Rides laana (Search ke liye)
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public List<Ride> searchRides(String source , String destination) {
        return rideRepository.findBySourceAndDestination(source, destination) ;
    }

    public Ride getRideById(Long id) {
        return rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride nahi mili"));
    }


    public List<Ride> searchRidesByPrice(String src, String dest) {
        return rideRepository.findBySourceAndDestinationOrderByPriceAsc(src, dest);
    }

    public List<Ride> searchRidesByTime(String src, String dest) {
        return rideRepository.findBySourceAndDestinationOrderByTravelDateAscStartTimeAsc(src, dest);
    }

    // Driver ki rides lana
    public List<Ride> getRidesByDriver(User driver) {
        return rideRepository.findByDriver(driver);
    }


}