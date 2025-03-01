
package com.example.generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="carSellerTransportWsDTO" type="{http://univaq.it/dandd/wsdltypes}CarSellerTransportWsDTO"/&gt;
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
    "carSellerTransportWsDTO"
})
@XmlRootElement(name = "getCarSellerByIdsResponse")
public class GetCarSellerByIdsResponse {

    @XmlElement(required = true)
    protected CarSellerTransportWsDTO carSellerTransportWsDTO;

    /**
     * Gets the value of the carSellerTransportWsDTO property.
     * 
     * @return
     *     possible object is
     *     {@link CarSellerTransportWsDTO }
     *     
     */
    public CarSellerTransportWsDTO getCarSellerTransportWsDTO() {
        return carSellerTransportWsDTO;
    }

    /**
     * Sets the value of the carSellerTransportWsDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CarSellerTransportWsDTO }
     *     
     */
    public void setCarSellerTransportWsDTO(CarSellerTransportWsDTO value) {
        this.carSellerTransportWsDTO = value;
    }

}
