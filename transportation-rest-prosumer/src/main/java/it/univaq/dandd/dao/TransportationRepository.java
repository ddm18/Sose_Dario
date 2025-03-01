package it.univaq.dandd.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import it.univaq.dandd.model.Transportation;

public interface TransportationRepository extends JpaRepository<Transportation, Long> {
	Optional<Transportation> findByDepartureHotelIdAndArrivalHotelId(Long departureHotelId, Long arrivalHotelId);
	void deleteByDepartureHotelIdOrArrivalHotelId(Long departureHotelId, Long arrivalHotelId);

}
