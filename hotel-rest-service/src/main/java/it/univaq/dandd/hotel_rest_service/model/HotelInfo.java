package it.univaq.dandd.hotel_rest_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "\"hotel_info\"", schema = "hotel_service_schema")
public class HotelInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_seq")
    @SequenceGenerator(name = "hotel_seq", sequenceName = "hotel_info_id_seq", allocationSize = 1)
    private long id;

    @NotBlank
    @Column(name = "hotel_name", nullable = false)
    private String hotelName;

    @NotBlank
    @Column(name = "location_name", nullable = false)
    private String locationName;

    @NotNull
    @Min(-90)
    @Max(90)
    @Column(name = "location_latitude", nullable = false)
    private Double locationLatitude;

    @NotNull
    @Min(-180)
    @Max(180)
    @Column(name = "location_longitude", nullable = false)
    private Double locationLongitude;

    public HotelInfo() {}

    public HotelInfo(long id, String hotelName, String locationName, Double locationLatitude, Double locationLongitude) {
        this.id = id;
        this.hotelName = hotelName;
        this.locationName = locationName;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(Double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public Double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(Double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }
}
