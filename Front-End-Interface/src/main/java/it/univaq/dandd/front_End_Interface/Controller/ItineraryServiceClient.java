package it.univaq.dandd.front_End_Interface.Controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;
import java.util.Map;

@FeignClient(name = "itineraryService", 
url = "http://api-gateway:8080/API-GATEWAY/ITINERARY-REST-SERVICE", 
fallback = ItineraryServiceClientFallback.class)
public interface ItineraryServiceClient {
	
	@GetMapping("/itinerary/all")
	List<Map<String, Object>> getAllBookings();
	
	@GetMapping("/itinerary/all_original")
	List<Map<String, Object>> getOriginalBookingInfo();
	
	@GetMapping("/itinerary/transportation/{hotelId1}/{hotelId2}")
	List<Map<String, Object>> getTransportationsBetweenHotels(
	@PathVariable int hotelId1,
	@PathVariable int hotelId2
	);
	
	@GetMapping("/itinerary/transportation")
	List<Map<String, Object>> getTransportationsBetweenLocations(
	@RequestParam String departure,
	@RequestParam String arrival
	);
	
	@GetMapping("/itinerary/show_cities")
	String getAllCities();
	
	@GetMapping("/itinerary/hotels")
	List<Map<String, Object>> getAllHotels();
	
	@PostMapping("/itinerary/transportation/store")
	String storeTransportationForItinerary(
	@RequestParam int hotelId1,
	@RequestParam int hotelId2,
	@RequestParam String serviceType,
	@RequestParam Long serviceId, 
	@RequestParam(required=false) Long serviceId2
	);
	
	@GetMapping("/itinerary/transportation/enrich")
	Map<String, Object> enrichItineraryTransportation(
	@RequestParam int departureHotelId,
	@RequestParam int arrivalHotelId
	);
	
	@PostMapping("/itinerary/store")
	String storeItinerary(@RequestParam int hotelId);
	
	@GetMapping("/itinerary/hotel_info/{Id}")
	ResponseEntity<Map<String, Object>> getHotelDetailsByItineraryId(@PathVariable int hotelId);
	
	@DeleteMapping("/itinerary/delete_all")
	ResponseEntity<String> deleteAllTransportation();
	
	@DeleteMapping("/itinerary/delete/by-hotel/{hotelId}")
	ResponseEntity<String> deleteItineraryAndTransportationsByHotelId(@PathVariable int hotelId);

}
