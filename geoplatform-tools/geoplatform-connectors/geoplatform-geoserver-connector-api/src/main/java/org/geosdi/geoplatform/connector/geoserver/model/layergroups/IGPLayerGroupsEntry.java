package org.geosdi.geoplatform.connector.geoserver.model.layergroups;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@JsonDeserialize(as = GPLayerGroupsEntry.class)
public interface IGPLayerGroupsEntry extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link String}
     */
    String getHref();

}
