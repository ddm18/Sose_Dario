package it.univaq.dandd.client;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class FlightServiceComm {

    private final FlightClient flightClient;

    public FlightServiceComm(FlightClient flightClient) {
        this.flightClient = flightClient;
    }

    /**
     * Fetch all flight routes.
     *
     * @return A list of all flight routes.
     */
    public List<Map<String, Object>> getAllRoutes() {
        return flightClient.getAllRoutes();
    }

    /**
     * Fetch future flight routes optionally filtered by departure and arrival cities.
     *
     * @param departure The departure city (optional).
     * @param arrival The arrival city (optional).
     * @return A list of future flight routes.
     */
    public List<Map<String, Object>> findRoutes(String departure, String arrival) {
        return flightClient.findRoutes(departure, arrival);
    }

    /**
     * Fetch a specific flight route by ID.
     *
     * @param id The ID of the flight route.
     * @return The flight route.
     */
    public Map<String, Object> findRouteById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid flight route ID.");
        }
        return flightClient.findRouteById(id);
    }

    /**
     * Fetch all flight routes by departure and arrival cities.
     *
     * @param departure The departure city.
     * @param arrival The arrival city.
     * @return A list of matching routes.
     */
    public List<Map<String, Object>> findFlightsByDepartureAndArrival(String departure, String arrival) {
        if (departure == null || departure.isBlank()) {
            throw new IllegalArgumentException("Departure location must not be null or blank.");
        }
        if (arrival == null || arrival.isBlank()) {
            throw new IllegalArgumentException("Arrival location must not be null or blank.");
        }
        return flightClient.findRoutesByDepartureAndArrival(departure, arrival);
    }

    /**
     * Get service status information.
     *
     * @return The service info as a string.
     */
    public String getInfo() {
        return flightClient.getInfo();
    }
}
