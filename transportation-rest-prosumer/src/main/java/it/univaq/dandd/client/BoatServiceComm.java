package it.univaq.dandd.client;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class BoatServiceComm {

    private final BoatClient boatClient;

    public BoatServiceComm(BoatClient boatClient) {
        this.boatClient = boatClient;
    }

    /**
     * Fetch routes by departure and arrival locations.
     * 
     * @param departure The departure location.
     * @param arrival The arrival location.
     * @return A list of routes matching the specified departure and arrival locations, represented as maps.
     */
    public List<Map<String, Object>> findBoatsByDepartureAndArrival(String departure, String arrival) {
        if (departure == null || departure.isBlank()) {
            throw new IllegalArgumentException("Departure location must not be null or blank.");
        }
        if (arrival == null || arrival.isBlank()) {
            throw new IllegalArgumentException("Arrival location must not be null or blank.");
        }
        return boatClient.getRoutesByDepartureAndArrival(departure, arrival);
    }

    /**
     * Fetch a specific route by its ID.
     * 
     * @param id The ID of the route.
     * @return The route represented as a map.
     */
    public Map<String, Object> findRouteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Route ID must not be null.");
        }
        return boatClient.getRouteById(id);
    }
}
