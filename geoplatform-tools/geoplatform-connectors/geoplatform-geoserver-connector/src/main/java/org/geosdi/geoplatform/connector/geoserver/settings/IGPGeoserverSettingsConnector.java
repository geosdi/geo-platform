package org.geosdi.geoplatform.connector.geoserver.settings;

import org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverLoadContactSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverLoadGlobalSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GeoserverUpdateGlobalSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.security.IGPGeoserverSecurityConnector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverSettingsConnector extends IGPGeoserverSecurityConnector {

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