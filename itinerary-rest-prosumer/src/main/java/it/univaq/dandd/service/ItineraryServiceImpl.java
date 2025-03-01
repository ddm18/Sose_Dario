package it.univaq.dandd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import it.univaq.dandd.dao.ItineraryRepository;
import it.univaq.dandd.model.ItineraryInfo;
import jakarta.transaction.Transactional;
import it.univaq.dandd.ItineraryDeletionBean;
import it.univaq.dandd.client.HotelClient;
import it.univaq.dandd.client.TransportationComm;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final HotelClient hotelClient; // Feign client for hotel service
    private final TransportationComm transportationComm; // Updated service layer for transportation
    
    @Autowired
    private ItineraryDeletionBean itineraryDeletionBean;

    public ItineraryServiceImpl(ItineraryRepository itineraryRepository, HotelClient hotelClient, TransportationComm transportationComm) {
        this.itineraryRepository = itineraryRepository;
        this.hotelClient = hotelClient;
        this.transportationComm = transportationComm;
    }

    @Override
    public List<ItineraryInfo> findAllBookings() {
        return itineraryRepository.findAllOrderedById();
    }

    @Override
    public Map<String, Object> askHotelProviderInfoById(ItineraryInfo itineraryInfo) {
        long id = itineraryInfo.getId();
        int hotelId = itineraryInfo.getHotelId();

        // Fetch details from the hotel service
        Map<String, Object> response = hotelClient.getHotelById((long) hotelId);
        response.put("itinerary_id", id);
        return response;
    }

    @Override
    public List<Map<String, Object>> findAllOriginalBookingInfo() {
        List<Map<String, Object>> originalData = new ArrayList<>();
        List<ItineraryInfo> itineraryData = itineraryRepository.findAllOrderedById();

        for (ItineraryInfo itineraryInfo : itineraryData) {
            Map<String, Object> response = askHotelProviderInfoById(itineraryInfo);
            originalData.add(response);
        }
        return originalData;
    }

    @Override
    public String showAllCities() {
        List<String> locationNames = new ArrayList<>();
        List<ItineraryInfo> itineraryData = itineraryRepository.findAllOrderedById();

        for (ItineraryInfo itineraryInfo : itineraryData) {
            Map<String, Object> response = askHotelProviderInfoById(itineraryInfo);
            String city = (String) response.get("locationName");
            locationNames.add(city);
        }

        return String.join(", ", locationNames);
    }

    @Override
    public List<Map<String, Object>> findTransportationsBetweenHotels(int id1, int id2) {
        // Fetch hotel details using the HotelClient
        Map<String, Object> hotel1Details = hotelClient.getHotelById((long) id1);
        Map<String, Object> hotel2Details = hotelClient.getHotelById((long) id2);

        String departureLocation = (String) hotel1Details.get("locationName");
        String arrivalLocation = (String) hotel2Details.get("locationName");

        if (departureLocation == null || arrivalLocation == null) {
            throw new IllegalArgumentException("One or both hotel locations are missing.");
        }

        return transportationComm.getTransportationsByRoute(departureLocation, arrivalLocation);
    }

    @Override
    public List<Map<String, Object>> findTransportationsBetweenLocations(String departure, String arrival) {
        if (departure == null || departure.isBlank() || arrival == null || arrival.isBlank()) {
            throw new IllegalArgumentException("Departure and Arrival locations must not be null or blank.");
        }

        List<Map<String, Object>> transportations = transportationComm.getTransportationsByRoute(departure, arrival);

        if (transportations.isEmpty()) {
            System.out.println("No transportation options found for route: " + departure + " -> " + arrival);
        }

        return transportations;
    }

    @Override
    public List<Map<String, Object>> getAllHotels() {
        List<Map<String, Object>> hotels = hotelClient.getAllHotels();

        if (hotels.isEmpty()) {
            System.out.println("No hotels are available at the moment.");
        }

        return hotels;
    }

    @Override
    public ItineraryInfo storeItinerary(int hotelId) {
        ItineraryInfo itineraryInfo = new ItineraryInfo();
        itineraryInfo.setHotelId(hotelId);

        return itineraryRepository.save(itineraryInfo);
    }

    @Override
    public Map<String, Object> getHotelByOriginalId(int hotelId) {
        Map<String, Object> hotelDetails = hotelClient.getHotelById((long) hotelId);

        if (hotelDetails == null || hotelDetails.isEmpty()) {
            throw new IllegalArgumentException("No details found for hotel with ID: " + hotelId);
        }

        return hotelDetails;
    }

    @Override
    public Map<String, Object> enrichItineraryTransportation(int departureHotelId, int arrivalHotelId) {
        Map<String, Object> enrichedTransportation = transportationComm.findAndEnrichTransportation((long) departureHotelId, (long) arrivalHotelId);

        if (enrichedTransportation == null || enrichedTransportation.isEmpty()) {
            throw new IllegalArgumentException("No enriched transportation details found for the given hotel IDs.");
        }

        return enrichedTransportation;
    }

    @Override
    public String storeTransportationForItinerary(int hotelId1, int hotelId2, String serviceType, Long serviceId, Long serviceId2) {
        System.out.println("STORING TRANSPORTATION");

        return transportationComm.storeTransportationByService(
                serviceType,
                serviceId,
                (long) hotelId1,
                (long) hotelId2,
                serviceId2
                
        );
    }

    @Override
    @DeleteMapping("/delete_all")
    public ResponseEntity<String> deleteAllItinerariesAndTransportations() {
        try {
            itineraryRepository.deleteAll();
            transportationComm.deleteAllTransportation();
            return new ResponseEntity<>("All itineraries and transportations have been deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Map<String, Object> getHotelDetailsByItineraryId(int itineraryId) {
        Optional<ItineraryInfo> itinerary = itineraryRepository.findById(itineraryId);

        if (!itinerary.isPresent() || itinerary.isEmpty()) {
            throw new IllegalArgumentException("No itinerary found for ID: " + itineraryId);
        }

        Map<String, Object> hotelDetails = hotelClient.getHotelById((long) itinerary.get().getHotelId());
        hotelDetails.put("itineraryId", itineraryId);

        return hotelDetails;
    }
    
    @Override
    public ResponseEntity<String> deleteItineraryAndTransportationsByHotelId(int hotelId) {
        try {
            ItineraryInfo itinerary = itineraryRepository.findByHotelId(hotelId);
            if (itinerary == null) {
                return new ResponseEntity<>(
                    "No itinerary found for hotel ID: " + hotelId, 
                    HttpStatus.NOT_FOUND
                );
            }

            CompletableFuture<Void> localDeleteFuture = CompletableFuture.runAsync(() -> {
                itineraryDeletionBean.deleteByHotelId(hotelId);
            });

            CompletableFuture<String> externalDeleteFuture = CompletableFuture.supplyAsync(() -> {
                return transportationComm.deleteTransportationById((long) hotelId);
            });

            CompletableFuture<String> combinedFuture = localDeleteFuture
                .thenCombineAsync(externalDeleteFuture, 
                    (voidResult, transportationResponse) -> 
                        "Itinerary and related transportations for hotel ID " 
                        + hotelId + " have been deleted.\n" 
                        + transportationResponse
                );

            String finalMessage = combinedFuture.join();
            return new ResponseEntity<>(finalMessage, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid input: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(
                "Unexpected error occurred: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


}