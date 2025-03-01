package it.univaq.dandd.flight_rest_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "flight_info", schema = "flight_service_schema")
public class FlightRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Maps to int4 in the database

    @NotBlank
    @Column(name = "departure_name", nullable = false, length = 100)
    private String departureName;

    @NotNull
    @Min(-90) @Max(90)
    @Column(name = "departure_latitude", nullable = false)
    private Double departureLatitude;

    @NotNull
    @Min(-180) @Max(180)
    @Column(name = "departure_longitude", nullable = false)
    private Double departureLongitude;

    @NotBlank
    @Column(name = "arrival_name", nullable = false, length = 100)
    private String arrivalName;

    @NotNull
    @Min(-90) @Max(90)
    @Column(name = "arrival_latitude", nullable = false)
    private Double arrivalLatitude;

    @NotNull
    @Min(-180) @Max(180)
    @Column(name = "arrival_longitude", nullable = false)
    private Double arrivalLongitude;

    @NotNull
    @Column(name = "departure_datetime", nullable = false)
    private LocalDateTime departureDatetime;

    @NotNull
    @Column(name = "arrival_datetime", nullable = false)
    private LocalDateTime arrivalDatetime;

    public FlightRoute() {}

    public FlightRoute(int id, @NotBlank String departureName, @NotNull @Min(-90) @Max(90) Double departureLatitude,
            @NotNull @Min(-180) @Max(180) Double departureLongitude, @NotBlank String arrivalName,
            @NotNull @Min(-90) @Max(90) Double arrivalLatitude, @NotNull @Min(-180) @Max(180) Double arrivalLongitude,
            @NotNull LocalDateTime departureDatetime, @NotNull LocalDateTime arrivalDatetime) {
        this.id = id;
        this.departureName = departureName;
        this.departureLatitude = departureLatitude;
        this.departureLongitude = departureLongitude;
        this.arrivalName = arrivalName;
        this.arrivalLatitude = arrivalLatitude;
        this.arrivalLongitude = arrivalLongitude;
        this.departureDatetime = departureDatetime;
        this.arrivalDatetime = arrivalDatetime;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureName() {
        return departureName;
    }

    public void setDepartureName(String departureName) {
        this.departureName = departureName;
    }

    public Double getDepartureLatitude() {
        return departureLatitude;
    }

    public void setDepartureLatitude(Double departureLatitude) {
        this.departureLatitude = departureLatitude;
    }

    public Double getDepartureLongitude() {
        return departureLongitude;
    }

    public void setDepartureLongitude(Double departureLongitude) {
        this.departureLongitude = departureLongitude;
    }

    public String getArrivalName() {
        return arrivalName;
    }

    public void setArrivalName(String arrivalName) {
        this.arrivalName = arrivalName;
    }

    public Double getArrivalLatitude() {
        return arrivalLatitude;
    }

    public void setArrivalLatitude(Double arrivalLatitude) {
        this.arrivalLatitude = arrivalLatitude;
    }

    public Double getArrivalLongitude() {
        return arrivalLongitude;
    }

    public void setArrivalLongitude(Double arrivalLongitude) {
        this.arrivalLongitude = arrivalLongitude;
    }

    public LocalDateTime getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(LocalDateTime departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public LocalDateTime getArrivalDatetime() {
        return arrivalDatetime;
    }

    public void setArrivalDatetime(LocalDateTime arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }
}
