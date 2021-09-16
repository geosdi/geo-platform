package org.geosdi.geoplatform.connector.geoserver.model.layergroups;

import java.io.Serializable;
import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPLayerGroups extends Serializable {

    /**
     * @return {@link List< IGPLayerGroupsEntry >}
     */
    List<IGPLayerGroupsEntry> getEntries();

}
