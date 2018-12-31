package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.GPGeoserverLoadDatastore;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverLoadDatastoreRequest extends GPConnectorRequest<GPGeoserverLoadDatastore> {

    /**
     * @return {@link String}
     */
    String getWorkspaceName();

    /**
     * @param theWorkspaceName
     */
    void setWorkspaceName(String theWorkspaceName);

    /**
     * @return {@link String}
     */
    String geStoreName();

    /**
     * @param theStoreName
     */
    void setStoreName(String theStoreName);

    /**
     * @return {@link Boolean}
     */
    Boolean isQuietNotFound();

    /**
     * <p>The quietOnNotFound parameter avoids logging an exception when the data store is not present.
     * Note that 404 status code will still be returned.
     * </p>
     *
     * @param theQuietNotFound
     */
    void setQuietNotFound(Boolean theQuietNotFound);
}