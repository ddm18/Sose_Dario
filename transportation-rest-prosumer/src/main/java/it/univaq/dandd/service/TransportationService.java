package it.univaq.dandd.service;

import it.univaq.dandd.exception.TransportationNotFoundException;
import it.univaq.dandd.model.Transportation;

import java.util.List;
import java.util.Map;

public interface TransportationService {



    List<Map<String, Object>> findTransportationOptions(String departure, String arrival);

    Transportation getTransportationById(Long id) throws TransportationNotFoundException;

    List<Transportation> getAllTransportations();


	Transportation findAndEnrichTransportation(Long departureHotelId, Long arrivalHotelId);


	void deleteAllTransportation();

	void storeTransportation(String departure, String arrival, String serviceType, Long serviceId,
			Long departureHotelId, Long arrivalHotelId, Long serviceId2, String carSeller);



	/**
	 * Stores a transportation entry by fetching service details using the service type and ID.
	 * 
	 * @param serviceType       The type of the service (e.g., "flight", "boat").
	 * @param serviceId         The ID of the service.
	 * @param departureHotelId  The ID of the departure hotel.
	 * @param arrivalHotelId    The ID of the arrival hotel.
	 */
	void storeTransportationByService(String serviceType, Long serviceId, Long departureHotelId, Long arrivalHotelId,
			Long serviceId2);

	void deleteTransportationByHotelId(Long hotelId);
}
