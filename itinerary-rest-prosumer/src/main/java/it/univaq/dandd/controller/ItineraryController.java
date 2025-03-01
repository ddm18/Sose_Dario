package it.univaq.dandd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.univaq.dandd.model.ItineraryInfo;
import it.univaq.dandd.service.ItineraryService;

@RestController
@RequestMapping("/itinerary")
public class ItineraryController {

    private final ItineraryService itineraryService;

    @Autowired 
    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @Operation(summary = "Return all bookings.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A JSON array with all bookings.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItineraryInfo.class))),
            @ApiResponse(responseCode = "204", description = "No bookings found.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error occurred.", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<List<ItineraryInfo>> findAllBookings() {
        try {
            List<ItineraryInfo> bookings = itineraryService.findAllBookings();
            if (bookings.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Return all original booking information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A JSON array with all original booking information.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "204", description = "No data found.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error occurred.", content = @Content)
    })
    @GetMapping("/all_original")
    public ResponseEntity<List<Map<String, Object>>> findOriginalBookingInfo() {
        try {
            List<Map<String, Object>> originalData = itineraryService.findAllOriginalBookingInfo();
            if (originalData.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(originalData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Find transportations between two hotels by hotel IDs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A JSON array with transportations.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid hotel IDs.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error occurred.", content = @Content)
    })
    @GetMapping("/transportation/{hotelId1}/{hotelId2}")
    public ResponseEntity<List<Map<String, Object>>> findTransportationsBetweenHotels(
            @PathVariable int hotelId1, @PathVariable int hotelId2) {
        try {
            List<Map<String, Object>> transportations = itineraryService.findTransportationsBetweenHotels(hotelId1, hotelId2);
            return ResponseEntity.ok(transportations);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Find transportations between two locations by names.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A JSON array with transportations.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid location names.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error occurred.", content = @Content)
    })
    @GetMapping("/transportation")
    public ResponseEntity<List<Map<String, Object>>> findTransportationsBetweenLocations(
            @RequestParam String departure, @RequestParam String arrival) {
        try {
            List<Map<String, Object>> transportations = itineraryService.findTransportationsBetweenLocations(departure, arrival);
            return ResponseEntity.ok(transportations);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Return all available cities.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A string with all cities.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Unexpected error occurred.", content = @Content)
    })
    @GetMapping("/show_cities")
    public ResponseEntity<String> showCities() {
        try {
            String cities = itineraryService.showAllCities();
            return ResponseEntity.ok(cities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Return all hotels.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A JSON array with all hotels.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Unexpected error occurred.", content = @Content)
    })
    @GetMapping("/hotels")
    public ResponseEntity<List<Map<String, Object>>> getAllHotels() {
        try {
            List<Map<String, Object>> hotels = itineraryService.getAllHotels();
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Store a new itinerary.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Itinerary successfully created.",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "Invalid hotel ID.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error occurred.", content = @Content)
    })
    @PostMapping("/store")
    public ResponseEntity<String> storeItinerary(@RequestParam int hotelId) {
        try {
            itineraryService.storeItinerary(hotelId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Itinerary successfully created.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred.");
        }
    }

    @Operation(summary = "Delete all itineraries and transportation records.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All transportation records have been deleted.",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Unexpected error occurred.", content = @Content)
    })
    @DeleteMapping("/delete_all")
    public ResponseEntity<String> deleteAllItinerariesandTransportation() {
        try {
            itineraryService.deleteAllItinerariesAndTransportations();
            return ResponseEntity.ok("All transportation records have been deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred: " + e.getMessage());
        }
    }
    @Operation(summary = "Find transportation details between two hotels.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully enriched transportation details.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid hotel IDs or no data found.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error occurred.", content = @Content)
    })
    @GetMapping("/transportation/enrich")
    public ResponseEntity<Map<String, Object>> enrichTransportation(
            @RequestParam int departureHotelId,
            @RequestParam int arrivalHotelId) {
        try {
            Map<String, Object> enrichedTransportation = itineraryService.enrichItineraryTransportation(departureHotelId, arrivalHotelId);
            return ResponseEntity.ok(enrichedTransportation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Unexpected error occurred."));
        }
    }
    
    @Operation(summary = "Store transportation details for a given itinerary.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transportation details stored successfully.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input data.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error occurred.", content = @Content)
    })
    @PostMapping("/transportation/store")
    public ResponseEntity<Map<String, Object>> storeTransportationForItinerary(
            @RequestParam int hotelId1,
            @RequestParam int hotelId2,
            @RequestParam String serviceType,
            @RequestParam Long serviceId,
            @RequestParam(required=false) Long serviceId2) {
        try {
            String result = itineraryService.storeTransportationForItinerary(hotelId1, hotelId2, serviceType, serviceId, serviceId2);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Transportation stored successfully.", "result", result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Unexpected error occurred."));
        }
    }
    
    @Operation(summary = "Delete itinerary and related transportation records by hotel ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itinerary and related transportations successfully deleted.",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "Invalid hotel ID.", content = @Content),
            @ApiResponse(responseCode = "404", description = "No itinerary found for the given hotel ID.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error occurred.", content = @Content)
    })
    @DeleteMapping("/delete/by-hotel/{hotelId}")
    public ResponseEntity<String> deleteItineraryAndTransportations(@PathVariable int hotelId) {
        return itineraryService.deleteItineraryAndTransportationsByHotelId(hotelId);
    }

}
