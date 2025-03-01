package it.univaq.dandd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.dandd.dao.ItineraryRepository;

@Service
public class ItineraryDeletionBean {

    @Autowired
    private ItineraryRepository itineraryRepository;

    /**
     * Delete itinerary in its own transaction.
     */
    @Transactional
    public void deleteByHotelId(int hotelId) {
        itineraryRepository.deleteByHotelId(hotelId);
    }
}
