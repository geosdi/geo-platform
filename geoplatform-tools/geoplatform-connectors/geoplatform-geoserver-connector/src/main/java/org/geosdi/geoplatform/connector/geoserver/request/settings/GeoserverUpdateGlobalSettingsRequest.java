package org.geosdi.geoplatform.connector.geoserver.request.settings;

import org.geosdi.geoplatform.connector.geoserver.model.settings.GPGeoserverGlobalSettings;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverUpdateGlobalSettingsRequest extends GPConnectorRequest<Boolean> {

    /**
     * @param theSettingsBody
     * @param <SettingsBody>
     * @return {@link GeoserverUpdateGlobalSettingsRequest}
     */
    <SettingsBody extends GPGeoserverGlobalSettings> GeoserverUpdateGlobalSettingsRequest withSettingsBody(@Nonnull(when = NEVER) SettingsBody theSettingsBody);
}