package it.univaq.dandd.front_End_Interface.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HotelTransportationController {

    private final ItineraryServiceClient itineraryServiceClient;

    public HotelTransportationController(ItineraryServiceClient itineraryServiceClient) {
        this.itineraryServiceClient = itineraryServiceClient;
    }

    @GetMapping("/hotels/transportation")
    public String showTransportationsBetweenHotels(
            @RequestParam int hotel1,
            @RequestParam int hotel2,
            Model model) {
        model.addAttribute("title", "Transportations Between Hotels");

        // Fetch available transportations
        var transportations = itineraryServiceClient.getTransportationsBetweenHotels(hotel1, hotel2);
        model.addAttribute("transportations", transportations);

        // Check if a transportation is already selected
        try {
            var selectedTransportation = itineraryServiceClient.enrichItineraryTransportation(hotel1, hotel2);
            System.out.println(selectedTransportation.get("id"));
            model.addAttribute("selectedTransportation", selectedTransportation);
        } catch (Exception e) {
            model.addAttribute("selectedTransportation", null);
        }
        
        model.addAttribute("input_hotel_1",hotel1);
        model.addAttribute("input_hotel_2",hotel2);
        return "transportation";
    }

    @PostMapping("/hotels/transportation/store")
    public String storeTransportation(
            @RequestParam int hotel1,
            @RequestParam int hotel2,
            @RequestParam String serviceType,
            @RequestParam Long serviceId,
            @RequestParam(required=false) Long serviceId2,
            Model model) {
        try {
            var result = itineraryServiceClient.storeTransportationForItinerary(hotel1, hotel2, serviceType, serviceId,serviceId2);
            model.addAttribute("message", result);
        } catch (Exception e) {
            model.addAttribute("message", "Failed to store transportation: " + e.getMessage());
        }

        return "redirect:/hotels/transportation?hotel1=" + hotel1 + "&hotel2=" + hotel2;
    }
}
