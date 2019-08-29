package org.geosdi.geoplatform.connector.store.settings;

import org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverLoadContactSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverLoadGlobalSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GeoserverUpdateGlobalSettingsRequest;
import org.geosdi.geoplatform.connector.store.security.GPGeoserverSecurityConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverSettingsConnectorStore extends GPGeoserverSecurityConnectorStore {

    /**
     * @return {@link org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverLoadGlobalSettingsRequest}
     */
    GPGeoserverLoadGlobalSettingsRequest loadGeoserverGlobalSettingRequest();

    /**
     * @return {@link GeoserverUpdateGlobalSettingsRequest}
     */
    GeoserverUpdateGlobalSettingsRequest updateGlobalSettingsRequest();

    /**
     * @return {@link GPGeoserverLoadContactSettingsRequest}
     */
    GPGeoserverLoadContactSettingsRequest loadGeoserverContactSettingsRequest();
}