package org.geosdi.geoplatform.connector.geoserver.request.security;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.security.GPGeoserverMasterPassword;
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
public class GPGeoserverGetMasterPasswordRequest extends GPJsonGetConnectorRequest<GPGeoserverMasterPassword> {

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverGetMasterPasswordRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("security/masterpw.json") : baseURI.concat("/security/masterpw.json")));
    }

    /**
     * @return {@link Class<GPGeoserverMasterPassword>}
     */
    @Override
    protected Class<GPGeoserverMasterPassword> forClass() {
        return GPGeoserverMasterPassword.class;
    }
}