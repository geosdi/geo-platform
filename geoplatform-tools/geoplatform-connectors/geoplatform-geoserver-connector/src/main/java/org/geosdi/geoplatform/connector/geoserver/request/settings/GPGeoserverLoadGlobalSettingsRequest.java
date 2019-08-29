package org.geosdi.geoplatform.connector.geoserver.request.settings;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.settings.GPGeoserverGlobalSettings;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadGlobalSettingsRequest extends GPJsonGetConnectorRequest<GPGeoserverGlobalSettings> {

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadGlobalSettingsRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("settings.json") : baseURI.concat("/settings.json")));
    }

    /**
     * @return {@link Class<GPGeoserverGlobalSettings>}
     */
    @Override
    protected Class<GPGeoserverGlobalSettings> forClass() {
        return GPGeoserverGlobalSettings.class;
    }
}