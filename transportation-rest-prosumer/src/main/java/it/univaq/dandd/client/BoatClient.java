package it.univaq.dandd.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "boat-rest-service", path = "/boats") // Matches the service ID and base path
public interface BoatClient {

    /**
     * Fetch routes by departure and arrival locations.
     * 
     * @param departure The departure location.
     * @param arrival The arrival location.
     * @return A list of routes represented as maps.
     */
    @GetMapping("/departure/{departure}/arrival/{arrival}/")
    List<Map<String, Object>> getRoutesByDepartureAndArrival(
            @PathVariable("departure") String departure,
            @PathVariable("arrival") String arrival);

    /**
     * Fetch a specific route by its ID.
     * 
     * @param id The ID of the route.
     * @return The route represented as a map.
     */
    @GetMapping("/{id}/")
    Map<String, Object> getRouteById(@PathVariable("id") Long id);
}
