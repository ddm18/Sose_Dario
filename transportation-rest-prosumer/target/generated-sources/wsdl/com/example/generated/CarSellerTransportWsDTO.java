
package com.example.generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CarSellerTransportWsDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CarSellerTransportWsDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="departureId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="arrivalId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="departureName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="departureLatitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="departureLongitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="arrivalName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="arrivalLatitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="arrivalLongitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="carSeller" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarSellerTransportWsDTO", propOrder = {
    "departureId",
    "arrivalId",
    "departureName",
    "departureLatitude",
    "departureLongitude",
    "arrivalName",
    "arrivalLatitude",
    "arrivalLongitude",
    "carSeller"
})
public class CarSellerTransportWsDTO {

    protected long departureId;
    protected long arrivalId;
    @XmlElement(required = true)
    protected String departureName;
    protected float departureLatitude;
    protected float departureLongitude;
    @XmlElement(required = true)
    protected String arrivalName;
    protected float arrivalLatitude;
    protected float arrivalLongitude;
    @XmlElement(required = true)
    protected String carSeller;

    /**
     * Gets the value of the departureId property.
     * 
     */
    public long getDepartureId() {
        return departureId;
    }

    /**
     * Sets the value of the departureId property.
     * 
     */
    public void setDepartureId(long value) {
        this.departureId = value;
    }

    /**
     * Gets the value of the arrivalId property.
     * 
     */
    public long getArrivalId() {
        return arrivalId;
    }

    /**
     * Sets the value of the arrivalId property.
     * 
     */
    public void setArrivalId(long value) {
        this.arrivalId = value;
    }

    /**
     * Gets the value of the departureName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureName() {
        return departureName;
    }

    /**
     * Sets the value of the departureName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureName(String value) {
        this.departureName = value;
    }

    /**
     * Gets the value of the departureLatitude property.
     * 
     */
    public float getDepartureLatitude() {
        return departureLatitude;
    }

    /**
     * Sets the value of the departureLatitude property.
     * 
     */
    public void setDepartureLatitude(float value) {
        this.departureLatitude = value;
    }

    /**
     * Gets the value of the departureLongitude property.
     * 
     */
    public float getDepartureLongitude() {
        return departureLongitude;
    }

    /**
     * Sets the value of the departureLongitude property.
     * 
     */
    public void setDepartureLongitude(float value) {
        this.departureLongitude = value;
    }

    /**
     * Gets the value of the arrivalName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalName() {
        return arrivalName;
    }

    /**
     * Sets the value of the arrivalName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalName(String value) {
        this.arrivalName = value;
    }

    /**
     * Gets the value of the arrivalLatitude property.
     * 
     */
    public float getArrivalLatitude() {
        return arrivalLatitude;
    }

    /**
     * Sets the value of the arrivalLatitude property.
     * 
     */
    public void setArrivalLatitude(float value) {
        this.arrivalLatitude = value;
    }

    /**
     * Gets the value of the arrivalLongitude property.
     * 
     */
    public float getArrivalLongitude() {
        return arrivalLongitude;
    }

    /**
     * Sets the value of the arrivalLongitude property.
     * 
     */
    public void setArrivalLongitude(float value) {
        this.arrivalLongitude = value;
    }

    /**
     * Gets the value of the carSeller property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarSeller() {
        return carSeller;
    }

    /**
     * Sets the value of the carSeller property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarSeller(String value) {
        this.carSeller = value;
    }

}
