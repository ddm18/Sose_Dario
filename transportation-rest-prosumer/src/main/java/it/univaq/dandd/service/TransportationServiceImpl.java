package it.univaq.dandd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.dandd.client.FlightServiceComm;
import it.univaq.dandd.client.BoatServiceComm;
import it.univaq.dandd.client.CarClientService;
import it.univaq.dandd.dao.TransportationRepository;
import it.univaq.dandd.exception.TransportationNotFoundException;
import it.univaq.dandd.model.Transportation;
import jakarta.annotation.PreDestroy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class TransportationServiceImpl implements TransportationService {

    private final TransportationRepository transportationRepository;
    private final FlightServiceComm flightServiceComm;
    private final BoatServiceComm boatServiceComm;
    private final CarClientService carClientService;

    @Autowired
    public TransportationServiceImpl(TransportationRepository transportationRepository,
                                      FlightServiceComm flightServiceComm,
                                      BoatServiceComm boatServiceComm, CarClientService carClientService) {
        this.transportationRepository = transportationRepository;
        this.flightServiceComm = flightServiceComm;
        this.boatServiceComm = boatServiceComm;
        this.carClientService = carClientService;
    }

    @Override
    public void storeTransportation(String departure, String arrival, String serviceType, Long serviceId, Long departureHotelId, Long arrivalHotelId, Long serviceId2, String carSeller) {
        if (departure == null || departure.isBlank() || arrival == null || arrival.isBlank() || serviceType == null || serviceType.isBlank()) {
            throw new IllegalArgumentException("Invalid input: Departure, Arrival, and Service Type must not be null or blank.");
        }

        Transportation transportation = new Transportation();
        transportation.setDepartureLocation(departure);
        transportation.setArrivalLocation(arrival);
        transportation.setServiceType(serviceType);
        transportation.setServiceId(serviceId);
        transportation.setDepartureHotelId(departureHotelId);
        transportation.setArrivalHotelId(arrivalHotelId);

        // Set additional fields for car service
        if ("car".equalsIgnoreCase(serviceType)) {
            transportation.setServiceId2(serviceId2);
            transportation.setCarSeller(carSeller);
        }

        transportationRepository.save(transportation);
    }


    @Override
    public List<Map<String, Object>> findTransportationOptions(String departure, String arrival) {
        if (departure == null || departure.isBlank() || arrival == null || arrival.isBlank()) {
            throw new IllegalArgumentException("Departure and Arrival locations must not be null or blank.");
        }

        // Flights future con error handling. 
        CompletableFuture<List<Map<String, Object>>> flightsFuture =
            CompletableFuture.supplyAsync(() -> flightServiceComm.findFlightsByDepartureAndArrival(departure, arrival))
                .thenApply(flightList -> {
                    flightList.forEach(f -> f.put("service_type", "flight"));
                    return flightList;
                })
                .exceptionally(ex -> {
                    System.err.println("Flight service failed: " + ex.getMessage());
                    return new ArrayList<>();
                });

        // Boats future 
        CompletableFuture<List<Map<String, Object>>> boatsFuture =
            CompletableFuture.supplyAsync(() -> boatServiceComm.findBoatsByDepartureAndArrival(departure, arrival))
                .thenApply(boatList -> {
                    boatList.forEach(b -> b.put("service_type", "boat"));
                    return boatList;
                })
                .exceptionally(ex -> {
                    System.err.println("Boat service failed: " + ex.getMessage());
                    return new ArrayList<>();
                });

        // Cars future 
        CompletableFuture<List<Map<String, Object>>> carsFuture =
            CompletableFuture.supplyAsync(() -> carClientService.getCommonCarSellersWithDetails(departure, arrival))
                .thenApply(carList -> {
                    carList.forEach(c -> c.put("service_type", "car"));
                    return carList;
                })
                .exceptionally(ex -> {
                    System.err.println("Car service failed: " + ex.getMessage());
                    return new ArrayList<>();
                });

        // End async
        CompletableFuture<Void> allDone = CompletableFuture.allOf(flightsFuture, boatsFuture, carsFuture);
        allDone.join(); // block until everything is finished

        // Unione risultati
        List<Map<String, Object>> transportationOptions = new ArrayList<>();
        transportationOptions.addAll(flightsFuture.join());
        transportationOptions.addAll(boatsFuture.join());
        transportationOptions.addAll(carsFuture.join());

        return transportationOptions;
    }




    @Override
    public Transportation getTransportationById(Long id) throws TransportationNotFoundException {
        return transportationRepository.findById(id)
                .orElseThrow(() -> new TransportationNotFoundException("Transportation with id " + id + " not found."));
    }

    @Override
    public List<Transportation> getAllTransportations() {
        return transportationRepository.findAll();
    }

    /**
     * Stores a transportation entry by fetching service details using the service type and ID.
     * 
     * @param serviceType       The type of the service (e.g., "flight", "boat").
     * @param serviceId         The ID of the service.
     * @param departureHotelId  The ID of the departure hotel.
     * @param arrivalHotelId    The ID of the arrival hotel.
     */
    @Override
    public void storeTransportationByService(String serviceType, Long serviceId, Long departureHotelId, Long arrivalHotelId, Long serviceId2) {
        if (serviceType == null || serviceType.isBlank()) {
            throw new IllegalArgumentException("Service type must not be null or blank.");
        }
        if (serviceId == null) {
            throw new IllegalArgumentException("Service ID must not be null.");
        }
        if (arrivalHotelId == null || departureHotelId == null) {
            throw new IllegalArgumentException("Hotel ids must not be null.");
        }
        if (serviceType == "car" && serviceId2 == null) {
            throw new IllegalArgumentException("For car Sellers service id2 must not be null.");
        }
        
        String carSeller = null;
        Map<String, Object> serviceDetails;

        // Fetch service details based on the service type
        switch (serviceType.toLowerCase()) {
            case "flight":
                serviceDetails = flightServiceComm.findRouteById(serviceId);
                break;
            case "boat":
                serviceDetails = boatServiceComm.findRouteById(serviceId);
                break;
            case "car":
                serviceDetails = carClientService.getCarSellerByIds(serviceId, serviceId2);
                carSeller=(String) serviceDetails.get("carSeller");
                break;
            default:
                throw new IllegalArgumentException("Unsupported service type: " + serviceType);
        }

        if (serviceDetails == null) {
            throw new IllegalArgumentException("Service details not found for ID: " + serviceId);
        }

        // Print all keys and values in serviceDetails
        System.out.println("Service Details:");
        for (Map.Entry<String, Object> entry : serviceDetails.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        String departure = (String) serviceDetails.get("departureName");
        String arrival = (String) serviceDetails.get("arrivalName");
        
        if (departure == null || departure.isBlank() || arrival == null || arrival.isBlank()) {
            throw new IllegalArgumentException("Service details must include valid departure and arrival locations.");
        }

        // Reuse existing storeTransportation method
        System.out.println("Storing");
        
        storeTransportation(departure, arrival, serviceType, serviceId, departureHotelId, arrivalHotelId, serviceId2, carSeller);
        System.out.println("Stored");
    }

    
    @Override
    public Transportation findAndEnrichTransportation(Long departureHotelId, Long arrivalHotelId) {
        if (departureHotelId == null || arrivalHotelId == null) {
            throw new IllegalArgumentException("Hotel IDs must not be null.");
        }

        // Directly fetch transportation from the repository
        return transportationRepository
                .findByDepartureHotelIdAndArrivalHotelId(departureHotelId, arrivalHotelId)
                .orElseThrow(() -> new TransportationNotFoundException(
                		"Transportation not found for the specified hotel IDs: departureHotelId=" + departureHotelId + ", arrivalHotelId=" + arrivalHotelId));
    }
    
    @Override
    public void deleteAllTransportation() {
        try {
        	transportationRepository.deleteAll();
            System.out.println("All transportations have been deleted.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting tranpsortaitons: " + e.getMessage(), e);
        }
    }
    
    @Override
    @Transactional
    public void deleteTransportationByHotelId(Long hotelId) {
        if (hotelId == null) {
            throw new IllegalArgumentException("Hotel ID must not be null.");
        }

        try {
            transportationRepository.deleteByDepartureHotelIdOrArrivalHotelId(hotelId,hotelId);
            System.out.println("Deleted all transportation records where the hotel ID matches either the departure or arrival hotel ID: " + hotelId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting transportation records: " + e.getMessage(), e);
        }
    }

   
   

}

