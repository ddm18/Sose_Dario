package it.univaq.dandd.client;

import org.springframework.stereotype.Service;
import com.example.generated.*;
import java.util.*;

@Service
public class CarClientService {

    private final CarSellerPortType carSellerPort;

    // Constructor injection for the SOAP client
    public CarClientService(CarSellerPortType carSellerPort) {
        this.carSellerPort = carSellerPort;
    }

    /**
     * Generic method to process responses and map DTOs to a list of maps.
     */
    private List<Map<String, Object>> mapTransportDetails(List<CarSellerTransportWsDTO> dtos) {
        List<Map<String, Object>> transportDetails = new ArrayList<>();
        for (CarSellerTransportWsDTO dto : dtos) {
            Map<String, Object> details = new HashMap<>();
            details.put("departureName", dto.getDepartureName());
            details.put("departureLatitude", dto.getDepartureLatitude());
            details.put("departureLongitude", dto.getDepartureLongitude());
            details.put("arrivalName", dto.getArrivalName());
            details.put("arrivalLatitude", dto.getArrivalLatitude());
            details.put("arrivalLongitude", dto.getArrivalLongitude());
            details.put("carSeller", dto.getCarSeller());
            details.put("id", dto.getDepartureId());
            details.put("id2", dto.getArrivalId());
            transportDetails.add(details);
        }
        return transportDetails;
    }

    /**
     * Retrieve all car sellers.
     *
     * @return A list of maps containing car seller details.
     */
    public List<Map<String, Object>> getAllCarSellers() {
        try {
            GetAllCarSellersRequest request = new GetAllCarSellersRequest();
            GetAllCarSellersResponse response = carSellerPort.getAllCarSellers(request);

            if (response != null && response.getCarSellers() != null) {
                List<Map<String, Object>> allCarSellers = new ArrayList<>();
                for (CarSeller carSeller : response.getCarSellers()) {
                    Map<String, Object> sellerDetails = new HashMap<>();
                    sellerDetails.put("id", carSeller.getId());
                    sellerDetails.put("carSeller", carSeller.getCarSeller());
                    sellerDetails.put("location", carSeller.getLocation());
                    sellerDetails.put("latitude", carSeller.getLatitude());
                    sellerDetails.put("longitude", carSeller.getLongitude());
                    allCarSellers.add(sellerDetails);
                }
                return allCarSellers;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving all car sellers: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    /**
     * Retrieve car seller transport details by departure and arrival IDs.
     *
     * @param departureId The departure ID.
     * @param arrivalId   The arrival ID.
     * @return A map containing the car seller transport details.
     */
    public Map<String, Object> getCarSellerByIds(Long departureId, Long arrivalId) {
        try {
            GetCarSellerByIdsRequest request = new GetCarSellerByIdsRequest();
            request.setDepartureId(departureId);
            request.setArrivalId(arrivalId);

            GetCarSellerByIdsResponse response = carSellerPort.getCarSellerByIds(request);

            if (response != null && response.getCarSellerTransportWsDTO() != null) {
                return mapTransportDetails(List.of(response.getCarSellerTransportWsDTO())).get(0);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving car seller by IDs: " + e.getMessage());
        }
        return Collections.emptyMap();
    }

    /**
     * Retrieve common car sellers with details between two locations.
     *
     * @param departure The departure location.
     * @param arrival   The arrival location.
     * @return A list of maps containing the car seller transport details.
     */
    public List<Map<String, Object>> getCommonCarSellersWithDetails(String departure, String arrival) {
        try {
            GetCommonCarSellersWithDetailsRequest request = new GetCommonCarSellersWithDetailsRequest();
            request.setDeparture(departure);
            request.setArrival(arrival);

            GetCommonCarSellersWithDetailsResponse response = carSellerPort.getCommonCarSellersWithDetails(request);

            if (response != null && response.getCarSellerTransportWsDTOs() != null) {
                List<Map<String, Object>> commonCarSellers = mapTransportDetails(response.getCarSellerTransportWsDTOs());
                // Adding service type
                commonCarSellers.forEach(details -> details.put("service_type", "car_seller"));
                return commonCarSellers;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving common car sellers: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
