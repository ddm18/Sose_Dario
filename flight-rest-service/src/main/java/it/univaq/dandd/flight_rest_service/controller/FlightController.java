package it.univaq.dandd.flight_rest_service.controller;

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
import it.univaq.dandd.flight_rest_service.model.FlightRoute;
import it.univaq.dandd.flight_rest_service.service.FlightRouteService;
import it.univaq.dandd.flight_rest_service.service.FlightRouteServiceImpl;

@RestController
@RequestMapping("/flights")
public class FlightController {
	
	//Service (injected)
	private final FlightRouteService flightRouteService;
	
	
	public FlightController(FlightRouteServiceImpl flightRouteServiceImpl) {
		this.flightRouteService = flightRouteServiceImpl;
	}
	
	@Operation(summary = "Return all flight routes currently registered.")
	@ApiResponses(value = { 
		  @ApiResponse(
				  responseCode = "200", 
				  description = "A JSON array with all registered flight routes.", 
				  content = { 
						  @Content(
								  mediaType = "application/json", 
								  array = @ArraySchema(schema = @Schema(implementation = FlightRoute.class))
								  ) 
				  }), 
		  @ApiResponse(
				  responseCode = "500",
				  description = "Unexpected error occured.", 
				  content = {
						  @Content(
								  mediaType = "application/json"
								  )
				  }) 
	})
	@GetMapping("/all/")
	public ResponseEntity<List<FlightRoute>> getAllRoutes(){
		return new ResponseEntity<List<FlightRoute>>(flightRouteService.findAllRoutes(), HttpStatus.OK);
	};

	@Operation(summary = "Return all future flight routes, eventually filtered by departing and/or arrriving city.")
	@ApiResponses(value = { 
		  @ApiResponse(
				  responseCode = "200", 
				  description = "A JSON array with all matching future routes.", 
				  content = { 
						  @Content(
								  mediaType = "application/json", 
								  array = @ArraySchema(schema = @Schema(implementation = FlightRoute.class))
								  ) 
				  }), 
		  @ApiResponse(
				  responseCode = "500",
				  description = "Unexpected error occured.", 
				  content = {
						  @Content(
								  mediaType = "application/json"
								  )
				  }) 
	})
	@GetMapping("/")
	public ResponseEntity<List<FlightRoute>> findRoutes(@RequestParam(required = false,defaultValue = "") String departure,@RequestParam(required = false, defaultValue = "") String arrival){
		return new ResponseEntity<List<FlightRoute>>(flightRouteService.findFutureRoutes(departure, arrival), HttpStatus.OK);
	};
	
	@Operation(summary = "Return a flight route matching this id.")
	@ApiResponses(value = { 
		  @ApiResponse(
				  responseCode = "200", 
				  description = "A JSON representing this flight route.", 
				  content = { 
						  @Content(
								  mediaType = "application/json", 
								  array = @ArraySchema(schema = @Schema(implementation = FlightRoute.class))
								  ) 
				  }),
		  @ApiResponse(
				  responseCode = "400",
				  description = "Invalid ID value.", 
				  content = {
						  @Content(
								  mediaType = "application/json"
								  )
				  }),
		  @ApiResponse(
				  responseCode = "404",
				  description = "Route not found for this ID.", 
				  content = {
						  @Content(
								  mediaType = "application/json"
								  )
				  }),
		  @ApiResponse(
				  responseCode = "500",
				  description = "Unexpected error occured.", 
				  content = {
						  @Content(
								  mediaType = "application/json"
								  )
				  }) 
	})
	
	@GetMapping("/{id}/")
	public ResponseEntity<FlightRoute> findRoute(@PathVariable("id") int id){
		return new ResponseEntity<FlightRoute>(flightRouteService.findRouteById(id), HttpStatus.OK);
	}
	
	@GetMapping("/info/")
	public String info() {
		return "running";
	}
		
	
	@Operation(summary = "Return all flight routes for a given departure and arrival city.")
	@ApiResponses(value = { 
	      @ApiResponse(
	              responseCode = "200", 
	              description = "A JSON array with all matching routes by departure and arrival city.", 
	              content = { 
	                      @Content(
	                              mediaType = "application/json", 
	                              array = @ArraySchema(schema = @Schema(implementation = FlightRoute.class))
	                              ) 
	              }), 
	      @ApiResponse(
	              responseCode = "400",
	              description = "Invalid departure or arrival city parameter.", 
	              content = {
	                      @Content(
	                              mediaType = "application/json"
	                              )
	              }), 
	      @ApiResponse(
	              responseCode = "500",
	              description = "Unexpected error occurred.", 
	              content = {
	                      @Content(
	                              mediaType = "application/json"
	                              )
	              }) 
	})
	@GetMapping("/departure/{departure}/arrival/{arrival}/")
	public ResponseEntity<List<FlightRoute>> findRoutesByDepartureAndArrival(
	        @PathVariable("departure") String departure, 
	        @PathVariable("arrival") String arrival) {
	    if (departure == null || departure.isBlank() || arrival == null || arrival.isBlank()) {
	        System.out.println("BBAAAADDD");
	    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	    return new ResponseEntity<>(flightRouteService.findRoutesByDepartureAndArrival(departure, arrival), HttpStatus.OK);
	}

}
