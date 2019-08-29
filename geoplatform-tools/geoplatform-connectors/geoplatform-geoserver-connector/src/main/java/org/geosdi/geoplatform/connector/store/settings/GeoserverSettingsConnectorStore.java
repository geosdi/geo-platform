package org.geosdi.geoplatform.connector.store.settings;

import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverLoadContactSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverLoadGlobalSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GeoserverUpdateGlobalSettingsRequest;
import org.geosdi.geoplatform.connector.store.security.GeoserverSecurityConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverSettingsConnectorStore extends GeoserverSecurityConnectorStore implements GPGeoserverSettingsConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverSettingsConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GPGeoserverLoadGlobalSettingsRequest}
     */
    @Override
    public GPGeoserverLoadGlobalSettingsRequest loadGeoserverGlobalSettingRequest() {
        return this.server.loadGeoserverGlobalSettingRequest();
    }

    /**
     * @return {@link GeoserverUpdateGlobalSettingsRequest}
     */
    @Override
    public GeoserverUpdateGlobalSettingsRequest updateGlobalSettingsRequest() {
        return this.server.updateGlobalSettingsRequest();
    }

    /**
     * @return {@link GPGeoserverLoadContactSettingsRequest}
     */
    @Override
    public GPGeoserverLoadContactSettingsRequest loadGeoserverContactSettingsRequest() {
        return this.server.loadGeoserverContactSettingsRequest();
    }
}