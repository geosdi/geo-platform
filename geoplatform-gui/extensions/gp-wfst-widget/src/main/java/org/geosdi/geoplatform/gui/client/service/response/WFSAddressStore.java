package org.geosdi.geoplatform.gui.client.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSAddressStore {

    @JsonProperty(value = "AddressDTO")
    public List<WFSAddressDTO> results = new ArrayList();

    @Override
    public String toString() {
        return "WFSAddressStore{" +
                "results=" + results +
                '}';
    }
}
