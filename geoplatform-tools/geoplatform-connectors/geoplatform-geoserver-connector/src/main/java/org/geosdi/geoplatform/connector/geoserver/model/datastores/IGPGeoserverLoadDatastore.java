package org.geosdi.geoplatform.connector.geoserver.model.datastores;

import org.geosdi.geoplatform.connector.geoserver.model.connection.GPGeoserverConnectionParameters;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverLoadDatastore extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link Boolean}
     */
    boolean isEnabled();

    /**
     * @return {@link IGPGeoserverDatastoreWorkspace}
     */
    IGPGeoserverDatastoreWorkspace getWorkspace();

    /**
     * @return {@link GPGeoserverConnectionParameters}
     */
    GPGeoserverConnectionParameters getConnectionParameters();

    /**
     * @return {@link Boolean}
     */
    boolean isDefault();

    /**
     * @return {@link String}
     */
    String getFeatureTypes();
}