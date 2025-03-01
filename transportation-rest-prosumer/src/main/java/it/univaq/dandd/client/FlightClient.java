package it.univaq.dandd.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "flight-rest-service", path = "/flights") // Matches the service ID and base path
public interface FlightClient {

    /**
     * Fetch all flight routes.
     *
     * @return A list of all flight routes represented as maps.
     */
    @GetMapping("/all/")
    List<Map<String, Object>> getAllRoutes();

    /**
     * Fetch future flight routes optionally filtered by departure and arrival cities.
     *
     * @param departure The departure city (optional).
     * @param arrival The arrival city (optional).
     * @return A list of future flight routes represented as maps.
     */
    @GetMapping("/")
    List<Map<String, Object>> findRoutes(
            @RequestParam(required = false, defaultValue = "") String departure,
            @RequestParam(required = false, defaultValue = "") String arrival);

    /**
     * Fetch a specific flight route by ID.
     *
     * @param id The ID of the flight route.
     * @return The flight route represented as a map.
     */
    @GetMapping("/{id}/")
    Map<String, Object> findRouteById(@PathVariable("id") Long id);

    /**
     * Fetch all flight routes by departure and arrival cities.
     *
     * @param departure The departure city.
     * @param arrival The arrival city.
     * @return A list of matching routes represented as maps.
     */
    @GetMapping("/departure/{departure}/arrival/{arrival}/")
    List<Map<String, Object>> findRoutesByDepartureAndArrival(
            @PathVariable("departure") String departure,
            @PathVariable("arrival") String arrival);

    /**
     * Get service status information.
     *
     * @return The service info as a string.
     */
    @GetMapping("/info/")
    String getInfo();
}
