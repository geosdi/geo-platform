package org.geosdi.geoplatform.gui.client.model.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

import java.io.Serializable;

import static org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressGeocodingKeyValue.DESCRIPTION;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@JsonRootName(value = "AddressDTO")
public class WFSAddressDTO implements Serializable {

    private static final long serialVersionUID = 3069410172889126088L;
    //
    private String displayName;
    private double longitude;
    private double latitude;

    public WFSAddressDTO() {
    }

    /**
     * @return {@link String}
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return {@link double}
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return {@link double}
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "WFSAddressDTO{" +
                "displayName='" + displayName + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
