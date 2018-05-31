package org.geosdi.geoplatform.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@XmlRootElement(name = "AddressDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressDTO {

    private String displayName;
    private double longitude;
    private double latitude;

    public AddressDTO() {
    }

    public AddressDTO(String displayName, double longitude, double latitude) {
        this.displayName = displayName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     *
     * @return {@link String}
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return {@link double}
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return {@link double}
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "displayName='" + displayName + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
