package org.geosdi.geoplatform.connector.store.security;

import org.geosdi.geoplatform.connector.geoserver.request.security.GPGeoserverGetMasterPasswordRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.GPGeoserverUpdateCatalogRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.catalog.GPGeoserverGetCatalogRequest;
import org.geosdi.geoplatform.connector.store.featuretypes.GPGeoserverFeatureTypesConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverSecurityConnectorStore extends GPGeoserverFeatureTypesConnectorStore {

    /**
     * @return {@link GPGeoserverGetMasterPasswordRequest}
     */
    GPGeoserverGetMasterPasswordRequest loadMasterPasswordRequest();

    /**
     * @return {@link GPGeoserverGetCatalogRequest}
     */
    GPGeoserverGetCatalogRequest loadCatalogRequest();

    /**
     * @return {@link GPGeoserverUpdateCatalogRequest}
     */
    GPGeoserverUpdateCatalogRequest updateCatalogRequest();
}