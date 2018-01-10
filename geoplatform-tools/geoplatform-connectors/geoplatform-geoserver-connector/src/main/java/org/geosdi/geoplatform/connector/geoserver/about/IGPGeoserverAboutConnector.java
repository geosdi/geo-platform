package org.geosdi.geoplatform.connector.geoserver.about;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverAboutConnector extends GPServerConnector {

    /**
     * @return {@link GPGeoserverAboutVersionRequest}
     */
    GPGeoserverAboutVersionRequest createAboutVersionRequest();

    /**
     * @return {@link GeoserverVersion}
     */
    @Override
    GeoserverVersion getVersion();
}