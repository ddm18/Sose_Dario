package it.univaq.dandd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.univaq.dandd.model.ItineraryInfo;

public interface ItineraryRepository extends JpaRepository<ItineraryInfo, Integer> {

    // Find itinerary by hotel ID
    ItineraryInfo findByHotelId(int hotelId);
    
    @Query("SELECT i FROM ItineraryInfo i ORDER BY i.id ASC")
    List<ItineraryInfo> findAllOrderedById();

    void deleteByHotelId(int hotelId);

}
