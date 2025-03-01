package it.univaq.dandd.front_End_Interface.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteController {

    private final ItineraryServiceClient itineraryServiceClient;

    public DeleteController(ItineraryServiceClient itineraryServiceClient) {
        this.itineraryServiceClient = itineraryServiceClient;
    }

    @PostMapping("/delete-itinerary")
    public String deleteItinerary(HttpSession session, @RequestParam(required = false) Integer hotel_to_delete) {
        if (hotel_to_delete != null) {
            // If a specific hotel ID is provided, delete that itinerary and related transportations
            itineraryServiceClient.deleteItineraryAndTransportationsByHotelId(hotel_to_delete);
        } else {
            // If no hotel ID is provided, delete all itineraries and transportations
            itineraryServiceClient.deleteAllTransportation();
        }
        return "redirect:/hotels"; // Redirect back to the hotels page
    }
}
