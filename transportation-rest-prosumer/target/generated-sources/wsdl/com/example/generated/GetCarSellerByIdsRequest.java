
package com.example.generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="departureId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="arrivalId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "departureId",
    "arrivalId"
})
@XmlRootElement(name = "getCarSellerByIdsRequest")
public class GetCarSellerByIdsRequest {

    protected long departureId;
    protected long arrivalId;

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

}
