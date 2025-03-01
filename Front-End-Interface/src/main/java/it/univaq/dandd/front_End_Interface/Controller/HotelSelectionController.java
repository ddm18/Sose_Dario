package it.univaq.dandd.front_End_Interface.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HotelSelectionController {

    private final ItineraryServiceClient itineraryServiceClient;

    public HotelSelectionController(ItineraryServiceClient itineraryServiceClient) {
        this.itineraryServiceClient = itineraryServiceClient;
    }

    @GetMapping("/hotels")
    public String showHotelForm(Model model, HttpSession session) {
        // Fetch all bookings from the backend
        List<Map<String, Object>> bookings = itineraryServiceClient.getOriginalBookingInfo();

        // Fetch all hotels
        List<Map<String, Object>> allHotels = itineraryServiceClient.getAllHotels();
        if (bookings== null) {
        	System.out.println("BOOKING IS NULL");
        	bookings = new ArrayList<>();
        }
        

        model.addAttribute("title", "Select Hotels");
        model.addAttribute("allHotels", allHotels);
        model.addAttribute("bookings", bookings);
        
        return "hotel_form.html";
    }

    @PostMapping("/hotels")
    public String processHotelSelection(@RequestParam String selectedHotelId) {
        try {
            // Store the selected hotel using the backend
            itineraryServiceClient.storeItinerary(Integer.parseInt(selectedHotelId));
        } catch (Exception e) {
            System.err.println("Failed to store hotel: " + e.getMessage());
        }

        return "redirect:/hotels";
    }
}
