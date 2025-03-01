package it.univaq.dandd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "itinerary_info")
public class ItineraryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "hotel_id", nullable = false)
    private int hotelId;

    // Default constructor
    public ItineraryInfo() {}

    // Constructor with parameters
    public ItineraryInfo(int id, int hotelId) {
        this.id = id;
        this.hotelId = hotelId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
