package org.geosdi.geoplatform.connector.store.about;

import org.geosdi.geoplatform.connector.api.GPConnectorStore;
import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverAboutConnectorStore extends GPConnectorStore<GPGeoserverConnector> implements GPGeoserverAboutConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverAboutConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GPGeoserverAboutVersionRequest}
     */
    @Override
    public GPGeoserverAboutVersionRequest createAboutVersionRequest() {
        return this.server.createAboutVersionRequest();
    }

    /**
     * @return {@link GPGeoserverAboutStatusRequest}
     */
    @Override
    public GPGeoserverAboutStatusRequest createAboutStatusRequest() {
        return this.server.createAboutStatusRequest();
    }
}