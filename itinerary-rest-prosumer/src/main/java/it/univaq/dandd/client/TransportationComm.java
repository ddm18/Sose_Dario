package it.univaq.dandd.client;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TransportationComm {

    private final TransportationClient transportationClient;

    public TransportationComm(TransportationClient transportationClient) {
        this.transportationClient = transportationClient;
    }

    /**
     * Fetch a specific transportation by ID.
     *
     * @param id The ID of the transportation to fetch.
     * @return A map representing the transportation details.
     */
    public Map<String, Object> getTransportationById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Transportation ID must not be null.");
        }
        return transportationClient.getTransportationById(id);
    }

    /**
     * Fetch all registered transportations.
     *
     * @return A list of maps representing all transportations.
     */
    public List<Map<String, Object>> getAllTransportations() {
        return transportationClient.getAllTransportations();
    }

    /**
     * Fetch transportations based on departure and arrival locations.
     *
     * @param departure The departure location.
     * @param arrival   The arrival location.
     * @return A list of maps representing matching transportations.
     */
    public List<Map<String, Object>> getTransportationsByRoute(String departure, String arrival) {
        if (departure == null || departure.isBlank()) {
            throw new IllegalArgumentException("Departure location must not be null or blank.");
        }
        if (arrival == null || arrival.isBlank()) {
            throw new IllegalArgumentException("Arrival location must not be null or blank.");
        }
        return transportationClient.getTransportationsByRoute(departure, arrival);
    }

    /**
     * Store a transportation entry by service type and ID.
     *
     * @param serviceType      The type of the service (e.g., "flight", "boat", "car").
     * @param serviceId        The ID of the service.
     * @param departureHotelId The departure hotel ID (optional).
     * @param arrivalHotelId   The arrival hotel ID (optional).
     * @param serviceId2       Optional second service ID for "car" type.
     * @param carSeller        Optional car seller name for "car" type.
     * @return A confirmation message of successful storage.
     */
    public String storeTransportationByService(String serviceType, Long serviceId, Long departureHotelId, Long arrivalHotelId, Long serviceId2) {
        if (serviceType == null || serviceType.isBlank()) {
            throw new IllegalArgumentException("Service type must not be null or blank.");
        }
        if (serviceId == null) {
            throw new IllegalArgumentException("Service ID must not be null.");
        }
        
        System.out.println("Passed store checks");

        // Pass all parameters, including optional ones, to the transportationClient
        return transportationClient.storeTransportationByService(serviceType, serviceId, departureHotelId, arrivalHotelId, serviceId2);
    }

    /**
     * Find and enrich a transportation entry based on departure and arrival hotel IDs.
     *
     * @param departureHotelId The departure hotel ID.
     * @param arrivalHotelId   The arrival hotel ID.
     * @return A map representing the enriched transportation details.
     */
    public Map<String, Object> findAndEnrichTransportation(Long departureHotelId, Long arrivalHotelId) {
        if (departureHotelId == null || arrivalHotelId == null) {
            throw new IllegalArgumentException("Hotel IDs must not be null.");
        }
        return transportationClient.findAndEnrichTransportation(departureHotelId, arrivalHotelId);
    }

    /**
     * Delete all transportation records.
     *
     * @return A confirmation message of successful deletion.
     */
    public String deleteAllTransportation() {
        return transportationClient.deleteAllTransportation();
    }
    
    public String deleteTransportationById(Long hotelId) {
    	if (hotelId == null) {
            throw new IllegalArgumentException("Hotel ID must not be null.");
        }
        return transportationClient.deleteTransportationByHotelId(hotelId);
    }
    
    
}
