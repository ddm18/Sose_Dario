package it.univaq.dandd.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "hotel-rest-service", path = "/hotels") // The name matches the Eureka service ID
public interface HotelClient {

    @GetMapping("/{id}/")
    Map<String, Object> getHotelById(@PathVariable("id") Long id);

    @GetMapping("/all/")
    List<Map<String, Object>> getAllHotels();
}
