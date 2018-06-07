package org.geosdi.geoplatform.gui.client.service.request;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeocodingAddressRequestDTO {

    public String lang;
    public String address;

    public GPGeocodingAddressRequestDTO() {
    }

    public GPGeocodingAddressRequestDTO(String lang, String address) {
        this.lang = lang;
        this.address = address;
    }

    @Override
    public String toString() {
        return "GPGeocodingAddressRequestDTO{" +
                "lang='" + lang + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
