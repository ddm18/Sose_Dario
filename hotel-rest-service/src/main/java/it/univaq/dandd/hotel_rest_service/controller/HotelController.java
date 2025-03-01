package it.univaq.dandd.hotel_rest_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.univaq.dandd.hotel_rest_service.model.HotelInfo;
import it.univaq.dandd.hotel_rest_service.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(summary = "Return all registered hotels.")
    @ApiResponses(value = { 
        @ApiResponse(
            responseCode = "200", 
            description = "A JSON array with all registered hotels.", 
            content = @Content(
                mediaType = "application/json", 
                array = @ArraySchema(schema = @Schema(implementation = HotelInfo.class))
            )
        ), 
        @ApiResponse(
            responseCode = "500", 
            description = "Unexpected error occurred.", 
            content = @Content(mediaType = "application/json")
        ) 
    })
    @GetMapping("/all/")
    public ResponseEntity<List<HotelInfo>> getAllHotels() {
        List<HotelInfo> hotels = hotelService.findAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @Operation(summary = "Get service information.")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Service is running.")
    })
    @GetMapping("/info/")
    public ResponseEntity<String> info() {
        return new ResponseEntity<>("running", HttpStatus.OK);
    }

    @Operation(summary = "Return registered hotels filtered by name and/or location.")
    @ApiResponses(value = { 
        @ApiResponse(
            responseCode = "200", 
            description = "A JSON array with matching hotels.", 
            content = @Content(
                mediaType = "application/json", 
                array = @ArraySchema(schema = @Schema(implementation = HotelInfo.class))
            )
        ), 
        @ApiResponse(
            responseCode = "500", 
            description = "Unexpected error occurred.", 
            content = @Content(mediaType = "application/json")
        ) 
    })
    @GetMapping("/")
    public ResponseEntity<List<HotelInfo>> getHotelsByName(
        @RequestParam(required = false, defaultValue = "") String location,
        @RequestParam(required = false, defaultValue = "") String name) {
        List<HotelInfo> hotels = hotelService.findSpecificHotels(location, name);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @Operation(summary = "Return a hotel matching the given ID.")
    @ApiResponses(value = { 
        @ApiResponse(
            responseCode = "200", 
            description = "A JSON representation of the hotel.", 
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = HotelInfo.class)
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Hotel not found for this ID.", 
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Unexpected error occurred.", 
            content = @Content(mediaType = "application/json")
        ) 
    })
    @GetMapping("/{id}/")
    public ResponseEntity<HotelInfo> findHotel(@PathVariable("id") long id) {
        try {
            HotelInfo hotel = hotelService.findHotelById(id);
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Return all hotels registered in the given location.")
    @ApiResponses(value = { 
        @ApiResponse(
            responseCode = "200", 
            description = "A JSON array with all hotels registered in the location.", 
            content = @Content(
                mediaType = "application/json", 
                array = @ArraySchema(schema = @Schema(implementation = HotelInfo.class))
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Invalid location parameter.", 
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Unexpected error occurred.", 
            content = @Content(mediaType = "application/json")
        ) 
    })
    @GetMapping("/location/{location}")
    public ResponseEntity<List<HotelInfo>> findHotelsByLocation(@PathVariable("location") String location) {
        if (location == null || location.isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<HotelInfo> hotels = hotelService.findHotelByLocation(location);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }
}
