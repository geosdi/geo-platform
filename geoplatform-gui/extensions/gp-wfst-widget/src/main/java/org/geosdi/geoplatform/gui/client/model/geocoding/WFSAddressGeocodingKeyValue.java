package org.geosdi.geoplatform.gui.client.model.geocoding;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum WFSAddressGeocodingKeyValue {

    DESCRIPTION("description"), LONGITUDE("lon"), LATITUDE("lat");

    private final String value;

    WFSAddressGeocodingKeyValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
