package it.univaq.dandd.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

@FeignClient(name = "transportation-rest-service", path = "/transportations") // The name matches the Eureka service ID
public interface TransportationClient {

    @GetMapping("/{id}/")
    Map<String, Object> getTransportationById(@PathVariable("id") Long id);

    @GetMapping("/all/")
    List<Map<String, Object>> getAllTransportations();

    @GetMapping("/")
    List<Map<String, Object>> getTransportationsByRoute(
        @RequestParam("departure") String departure,
        @RequestParam("arrival") String arrival
    );

    @PostMapping("/store/by-service/")
    String storeTransportationByService(
            @RequestParam("serviceType") String serviceType,
            @RequestParam("serviceId") Long serviceId,
            @RequestParam("departureHotelId") Long departureHotelId,
            @RequestParam("arrivalHotelId") Long arrivalHotelId,
            @RequestParam(value = "serviceId2", required = false) Long serviceId2
      
    );



    @GetMapping("/enrich/")
    Map<String, Object> findAndEnrichTransportation(
        @RequestParam("departureHotelId") Long departureHotelId,
        @RequestParam("arrivalHotelId") Long arrivalHotelId
    );
    

    @DeleteMapping("/delete_all")
    String deleteAllTransportation();
    

    @DeleteMapping("/delete/by-hotel/{hotelId}")
    String deleteTransportationByHotelId(@PathVariable("hotelId") Long hotelId);
    
    
}
