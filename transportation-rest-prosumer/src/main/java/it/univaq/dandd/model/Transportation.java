package it.univaq.dandd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "transportation", schema = "transportation_prosumer_schema")
public class Transportation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Column(name = "departure_location", nullable = false)
    private String departureLocation;

    @NotBlank
    @Column(name = "arrival_location", nullable = false)
    private String arrivalLocation;

    @NotBlank
    @Column(name = "service_type", nullable = false)
    private String serviceType;

    @NotNull
    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Column(name = "departure_hotel_id", nullable = true)
    private Long departureHotelId;

    @Column(name = "arrival_hotel_id", nullable = true)
    private Long arrivalHotelId;
    
    @Column(name = "service_id2", nullable = true)
    private Long serviceId2;
    
    @Column(name = "car_seller", nullable = true)
    private String carSeller;

    // Default constructor
    public Transportation() {}

    // Constructor with fields
    public Transportation(String departureLocation, String arrivalLocation,
                           String serviceType, Long serviceId, Long departureHotelId, Long arrivalHotelId,Long serviceId2,
                           String carSeller) {
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.serviceType = serviceType;
        this.serviceId = serviceId;
        this.departureHotelId = departureHotelId;
        this.arrivalHotelId = arrivalHotelId;
        this.serviceId2 = serviceId2;
        this.carSeller = carSeller;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getDepartureHotelId() {
        return departureHotelId;
    }

    public void setDepartureHotelId(Long departureHotelId) {
        this.departureHotelId = departureHotelId;
    }

    public Long getArrivalHotelId() {
        return arrivalHotelId;
    }

    public void setArrivalHotelId(Long arrivalHotelId) {
        this.arrivalHotelId = arrivalHotelId;
    }
    public Long getServiceId2() {
        return serviceId2;
    }

    public void setServiceId2(Long serviceId2) {
        this.serviceId2 = serviceId2;
    }
    
    public String getCarSeller() {
        return carSeller;
    }

    public void setCarSeller(String carSeller) {
        this.carSeller = carSeller;
    }
}
