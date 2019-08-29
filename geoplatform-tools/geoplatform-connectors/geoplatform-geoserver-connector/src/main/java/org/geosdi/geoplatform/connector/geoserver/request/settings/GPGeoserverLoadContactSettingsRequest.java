package org.geosdi.geoplatform.connector.geoserver.request.settings;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.settings.contact.GPGeoserverContactSettings;
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
public class GPGeoserverLoadContactSettingsRequest extends GPJsonGetConnectorRequest<GPGeoserverContactSettings> {

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadContactSettingsRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("settings/contact.json") : baseURI.concat("/settings/contact.json")));
    }

    /**
     * @return {@link Class<GPGeoserverContactSettings>}
     */
    @Override
    protected Class<GPGeoserverContactSettings> forClass() {
        return GPGeoserverContactSettings.class;
    }
}