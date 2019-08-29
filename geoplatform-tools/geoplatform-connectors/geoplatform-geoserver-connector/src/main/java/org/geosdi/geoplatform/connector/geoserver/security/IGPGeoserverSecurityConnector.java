package org.geosdi.geoplatform.connector.geoserver.security;

import org.geosdi.geoplatform.connector.geoserver.featuretypes.IGPGeoserverFeatureTypesConnector;
import org.geosdi.geoplatform.connector.geoserver.request.security.GPGeoserverGetMasterPasswordRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.GPGeoserverUpdateCatalogRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.catalog.GPGeoserverGetCatalogRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverSecurityConnector extends IGPGeoserverFeatureTypesConnector {

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