package org.geosdi.geoplatform.connector.store.security;

import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.security.GPGeoserverGetMasterPasswordRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.GPGeoserverUpdateCatalogRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.catalog.GPGeoserverGetCatalogRequest;
import org.geosdi.geoplatform.connector.store.featuretypes.GeoserverFeatureTypesConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverSecurityConnectorStore extends GeoserverFeatureTypesConnectorStore implements GPGeoserverSecurityConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverSecurityConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GPGeoserverGetMasterPasswordRequest}
     */
    @Override
    public GPGeoserverGetMasterPasswordRequest loadMasterPasswordRequest() {
        return this.server.loadMasterPasswordRequest();
    }

    /**
     * @return {@link GPGeoserverGetCatalogRequest}
     */
    @Override
    public GPGeoserverGetCatalogRequest loadCatalogRequest() {
        return this.server.loadCatalogRequest();
    }

    /**
     * @return {@link GPGeoserverUpdateCatalogRequest}
     */
    @Override
    public GPGeoserverUpdateCatalogRequest updateCatalogRequest() {
        return this.server.updateCatalogRequest();
    }
}