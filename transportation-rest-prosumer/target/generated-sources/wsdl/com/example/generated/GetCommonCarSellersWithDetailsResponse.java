
package com.example.generated;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="carSellerTransportWsDTOs" type="{http://univaq.it/dandd/wsdltypes}CarSellerTransportWsDTO" maxOccurs="unbounded"/&gt;
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
    "carSellerTransportWsDTOs"
})
@XmlRootElement(name = "getCommonCarSellersWithDetailsResponse")
public class GetCommonCarSellersWithDetailsResponse {

    @XmlElement(required = true)
    protected List<CarSellerTransportWsDTO> carSellerTransportWsDTOs;

    /**
     * Gets the value of the carSellerTransportWsDTOs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the carSellerTransportWsDTOs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCarSellerTransportWsDTOs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CarSellerTransportWsDTO }
     * 
     * 
     */
    public List<CarSellerTransportWsDTO> getCarSellerTransportWsDTOs() {
        if (carSellerTransportWsDTOs == null) {
            carSellerTransportWsDTOs = new ArrayList<CarSellerTransportWsDTO>();
        }
        return this.carSellerTransportWsDTOs;
    }

}
