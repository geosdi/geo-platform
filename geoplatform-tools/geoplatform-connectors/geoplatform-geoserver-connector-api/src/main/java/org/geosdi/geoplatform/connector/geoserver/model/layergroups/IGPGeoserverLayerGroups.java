package org.geosdi.geoplatform.connector.geoserver.model.layergroups;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@XmlRootElement(name = "layerGroups")
@JsonDeserialize(as = GPGeoserverLayerGroups.class)
public interface IGPGeoserverLayerGroups extends Serializable {

    /**
     * @return {@link List<IGPGeoserverLayerGroup>}
     */
    List<IGPGeoserverLayerGroup> getLayers();

    /**
     * @return {@link Boolean}
     */
    @XmlTransient
    default boolean isSetLayers() {
        return ((this.getLayers() != null) && !(this.getLayers().isEmpty()));
    }
}