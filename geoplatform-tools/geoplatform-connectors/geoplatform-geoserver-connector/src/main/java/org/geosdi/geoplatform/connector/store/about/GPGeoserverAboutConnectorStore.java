package org.geosdi.geoplatform.connector.store.about;

import org.geosdi.geoplatform.connector.api.GeoPlatformConnector;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverAboutConnectorStore extends GeoPlatformConnector {

    /**
     * @return {@link GPGeoserverAboutVersionRequest}
     */
    GPGeoserverAboutVersionRequest createAboutVersionRequest();

    /**
     * @return {@link GPGeoserverAboutStatusRequest}
     */
    GPGeoserverAboutStatusRequest createAboutStatusRequest();
}