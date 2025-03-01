package it.univaq.dandd.model;

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
@Table(name = "car_sellers")
public class CarSellersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column(name = "car_seller", nullable = false)
    private String carSeller;
    
    @NotBlank
    @Column(name = "location", nullable = false)
    private String location;

    @NotNull
    @Min(-90) @Max(90)
    @Column(name = "latitude", nullable = false, precision = 9)
    private Float latitude;

    @NotNull
    @Min(-180) @Max(180)
    @Column(name = "longitude", nullable = false, precision = 10)
    private Float longitude;

    public CarSellersModel() {}

    public CarSellersModel(long id, String carSeller, Float latitude, Float longitude) {
        this.id = id;
        this.carSeller = carSeller;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarSeller() {
        return carSeller;
    }
    
    public void setCarSeller(String carSeller) {
        this.carSeller = carSeller;
    }
    
    public String getLocation() {
    	return location;
    }

    public void setLocation(String location) {
    	this.location = location;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
