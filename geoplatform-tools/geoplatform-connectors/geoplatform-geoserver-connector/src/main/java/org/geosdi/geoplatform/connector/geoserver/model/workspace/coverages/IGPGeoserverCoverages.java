package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverCoverages extends Serializable {

    /**
     * @param <Coverage>
     * @return {@link List<Coverage>}
     */
    <Coverage extends IGPGeoserverCoverage> List<Coverage> getCoverage();
}
