package org.geosdi.geoplatform.gui.client.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class FeatureCollectionResponse {

    @JsonProperty(value = "features")
    public List<FeatureDTO> featureCollectionDTOS = new ArrayList<>();

    @Override
    public String toString() {
        return "FeatureCollectionResponse{" +
                "featureCollectionDTOS=" + featureCollectionDTOS +
                '}';
    }
}
