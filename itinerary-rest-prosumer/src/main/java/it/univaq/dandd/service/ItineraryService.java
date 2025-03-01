package it.univaq.dandd.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import it.univaq.dandd.model.ItineraryInfo;

public interface ItineraryService {

	


	Map<String, Object> askHotelProviderInfoById(ItineraryInfo itineraryInfo);


	/**
	 * Fetch available transportation options between departure and arrival locations.
	 *
	 * @param departure The departure location.
	 * @param arrival The arrival location.
	 * @return A list of transportation options available for the route.
	 */
	List<Map<String, Object>> findTransportationsBetweenLocations(String departure, String arrival);

	/**
	 * Fetch locations from itinerary DB using hotel IDs and find transportation options.
	 *
	 * @param hotelId1 The ID of the first hotel.
	 * @param hotelId2 The ID of the second hotel.
	 * @return A list of transportation options between the two hotel locations.
	 */
	List<Map<String, Object>> findTransportationsBetweenHotels(int hotelId1, int hotelId2);

	List<Map<String, Object>> getAllHotels();

	ItineraryInfo storeItinerary(int hotelId);

	

	


	List<ItineraryInfo> findAllBookings();


	


	List<Map<String, Object>> findAllOriginalBookingInfo();


	String showAllCities();


	Map<String, Object> getHotelByOriginalId(int hotelId);


	Map<String, Object> enrichItineraryTransportation(int departureHotelId, int arrivalHotelId);




	ResponseEntity<String> deleteAllItinerariesAndTransportations();


	Map<String, Object> getHotelDetailsByItineraryId(int hotelId);





	String storeTransportationForItinerary(int hotelId1, int hotelId2, String serviceType, Long serviceId,
			Long serviceId2);


	ResponseEntity<String> deleteItineraryAndTransportationsByHotelId(int hotelId);


}
