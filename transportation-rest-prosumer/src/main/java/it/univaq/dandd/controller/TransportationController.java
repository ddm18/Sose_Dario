package it.univaq.dandd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.univaq.dandd.model.Transportation;
import it.univaq.dandd.service.TransportationService;
import it.univaq.dandd.exception.TransportationNotFoundException;

@RestController
@RequestMapping("/transportations")
public class TransportationController {

    private final TransportationService transportationService;

    public TransportationController(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    @Operation(summary = "Return all registered transportations.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "A JSON array with all transportations registered.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Transportation.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected error occurred.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/all/")
    public ResponseEntity<List<Transportation>> getAllTransportations() {
        return new ResponseEntity<>(transportationService.getAllTransportations(), HttpStatus.OK);
    }

    @GetMapping("/info/")
    public String info() {
        return "Transportation service is running.";
    }

    @Operation(summary = "Return transportation options filtered by departure and/or arrival location.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "A JSON array with all matching transportations.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected error occurred.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/")
    public ResponseEntity<List<Map<String, Object>>> getTransportationOptions(
            @RequestParam(required = false, defaultValue = "") String departure,
            @RequestParam(required = false, defaultValue = "") String arrival) {
        return new ResponseEntity<>(transportationService.findTransportationOptions(departure, arrival), HttpStatus.OK);
    }

    @Operation(summary = "Return the transportation with the given ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "A JSON representing this transportation.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Transportation.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "The ID path parameter is invalid.",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Transportation not found for this ID.",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected error occurred.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/{id}/")
    public ResponseEntity<Transportation> findTransportationById(@PathVariable long id) {
        Transportation transportation = transportationService.getTransportationById(id);
        return new ResponseEntity<>(transportation, HttpStatus.OK);
    }


    @Operation(summary = "Store a new transportation entry.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The transportation entry has been successfully stored.",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data.",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected error occurred.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/store/")
    public ResponseEntity<String> storeTransportation(
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam String serviceType,
            @RequestParam Long serviceId,
            @RequestParam Long departureHotelId,
            @RequestParam Long arrivalHotelId,
            @RequestParam(required = false) Long serviceId2,
            @RequestParam(required = false) String carSeller) {
        transportationService.storeTransportation(departure, arrival, serviceType, serviceId, departureHotelId, arrivalHotelId,
                serviceId2, carSeller);
        return new ResponseEntity<>("Transportation entry stored successfully.", HttpStatus.OK);
    }


    @Operation(summary = "Store a transportation entry by service type and ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The transportation entry has been successfully stored.",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data.",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected error occurred.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/store/by-service/")
    public ResponseEntity<String> storeTransportationByService(
            @RequestParam String serviceType,
            @RequestParam Long serviceId,
            @RequestParam Long departureHotelId,
            @RequestParam Long arrivalHotelId,
            @RequestParam(required = false) Long serviceId2 // Optional parameter
    ) {
        try {
            // Call the service method with the provided parameters
            transportationService.storeTransportationByService(serviceType, serviceId, departureHotelId, arrivalHotelId, serviceId2);

            return new ResponseEntity<>("Transportation entry stored successfully using service details.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid input: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Find and enrich transportation based on departure and arrival hotel IDs.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "A JSON representation of the enriched transportation details.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Transportation.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data.",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Transportation not found for the given hotel IDs.",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected error occurred.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/enrich/")
    public ResponseEntity<Transportation> findAndEnrichTransportation(
            @RequestParam Long departureHotelId,
            @RequestParam Long arrivalHotelId) {
        try {
            Transportation transportation = transportationService.findAndEnrichTransportation(departureHotelId, arrivalHotelId);
            return new ResponseEntity<>(transportation, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (TransportationNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Operation(summary = "Delete all transportation records.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All transportation records have been deleted."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected error occurred."
            )
    })
    @DeleteMapping("/delete_all")
    public ResponseEntity<String> deleteAllTransportation() {
        try {
            transportationService.deleteAllTransportation();
            return new ResponseEntity<>("All transportation records have been deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/delete/by-hotel/{hotelId}")
    public ResponseEntity<String> deleteTransportationByHotelId(@PathVariable Long hotelId) {
        try {
            transportationService.deleteTransportationByHotelId(hotelId);
            return new ResponseEntity<>("Transportation records for hotel ID " + hotelId + " have been deleted.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid input: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

}
