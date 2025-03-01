package it.univaq.dandd.front_End_Interface.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
class ItineraryServiceClientFallback implements ItineraryServiceClient {

    @Override
    public List<Map<String, Object>> getAllBookings() {
        return Collections.singletonList(Map.of("error", "Unable to fetch bookings. Service is unavailable."));
    }

    @Override
    public List<Map<String, Object>> getOriginalBookingInfo() {
        return Collections.singletonList(Map.of("error", "Unable to fetch original booking info. Service is unavailable."));
    }

    @Override
    public List<Map<String, Object>> getTransportationsBetweenHotels(int hotelId1, int hotelId2) {
        return Collections.singletonList(Map.of(
                "error", "Unable to fetch transportation options between hotels.",
                "hotelId1", hotelId1,
                "hotelId2", hotelId2
        ));
    }

    @Override
    public List<Map<String, Object>> getTransportationsBetweenLocations(String departure, String arrival) {
        return Collections.singletonList(Map.of(
                "error", "Unable to fetch transportation options between locations.",
                "departure", departure,
                "arrival", arrival
        ));
    }

    @Override
    public String getAllCities() {
        return "Unable to fetch cities. Service is unavailable.";
    }

    @Override
    public List<Map<String, Object>> getAllHotels() {
        return Collections.singletonList(Map.of("error", "Unable to fetch hotels. Service is unavailable."));
    }



    @Override
    public Map<String, Object> enrichItineraryTransportation(int departureHotelId, int arrivalHotelId) {
        return Map.of(
                "error", "Unable to enrich transportation details.",
                "departureHotelId", departureHotelId,
                "arrivalHotelId", arrivalHotelId
        );
    }

	@Override
	public String storeItinerary(int hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String storeTransportationForItinerary(int hotelId1, int hotelId2, String serviceType, Long serviceId,
			Long serviceId2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Map<String, Object>> getHotelDetailsByItineraryId(int hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteAllTransportation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteItineraryAndTransportationsByHotelId(int hotelId) {
		// TODO Auto-generated method stub
		return null;
	}
}
