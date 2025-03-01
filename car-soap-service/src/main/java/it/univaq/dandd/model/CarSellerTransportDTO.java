package it.univaq.dandd.model;

public class CarSellerTransportDTO {
    private String departureName;
    private Float departureLatitude;
    private Float departureLongitude;
    private String arrivalName;
    private Float arrivalLatitude;
    private Float arrivalLongitude;
    private String serviceType; // Optional if you want to include it
    private Long departureId; // New field
    private Long arrivalId;   // New field
    private String carSeller; // Added field

    public CarSellerTransportDTO(String departureName, Float departureLatitude, Float departureLongitude,
                                 String arrivalName, Float arrivalLatitude, Float arrivalLongitude,
                                 Long departureId, Long arrivalId, String carSeller) {
        this.departureName = departureName;
        this.departureLatitude = departureLatitude;
        this.departureLongitude = departureLongitude;
        this.arrivalName = arrivalName;
        this.arrivalLatitude = arrivalLatitude;
        this.arrivalLongitude = arrivalLongitude;
        this.departureId = departureId;
        this.arrivalId = arrivalId;
        this.carSeller = carSeller;
    }

    // Getters and Setters
    public String getDepartureName() {
        return departureName;
    }

    public void setDepartureName(String departureName) {
        this.departureName = departureName;
    }

    public Float getDepartureLatitude() {
        return departureLatitude;
    }

    public void setDepartureLatitude(Float departureLatitude) {
        this.departureLatitude = departureLatitude;
    }

    public Float getDepartureLongitude() {
        return departureLongitude;
    }

    public void setDepartureLongitude(Float departureLongitude) {
        this.departureLongitude = departureLongitude;
    }

    public String getArrivalName() {
        return arrivalName;
    }

    public void setArrivalName(String arrivalName) {
        this.arrivalName = arrivalName;
    }

    public Float getArrivalLatitude() {
        return arrivalLatitude;
    }

    public void setArrivalLatitude(Float arrivalLatitude) {
        this.arrivalLatitude = arrivalLatitude;
    }

    public Float getArrivalLongitude() {
        return arrivalLongitude;
    }

    public void setArrivalLongitude(Float arrivalLongitude) {
        this.arrivalLongitude = arrivalLongitude;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Long getDepartureId() {
        return departureId;
    }

    public void setDepartureId(Long departureId) {
        this.departureId = departureId;
    }

    public Long getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(Long arrivalId) {
        this.arrivalId = arrivalId;
    }

    public String getCarSeller() {
        return carSeller;
    }

    public void setCarSeller(String carSeller) {
        this.carSeller = carSeller;
    }
}
